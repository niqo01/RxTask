package com.nicolasmilliard.rxtask;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.SingleObserver;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

final class SingleTaskCallback<T> extends TaskDisposable implements
        OnCompleteListener<T> {

    private final SingleObserver<? super T> observer;

    public SingleTaskCallback(Task<?> task, SingleObserver<? super T> observer) {
        super(task);
        this.observer = observer;
    }

    @Override
    public void onComplete(@NonNull Task<T> task) {
        if (isDisposed()) return;
        if (task.isSuccessful()) {
            observer.onSuccess(task.getResult());
        } else {
            try {
                observer.onError(task.getException());
            } catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                RxJavaPlugins.onError(new CompositeException(task.getException(), t));
            }
        }
    }
}