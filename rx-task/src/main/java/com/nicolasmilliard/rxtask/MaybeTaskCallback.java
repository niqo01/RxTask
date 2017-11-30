package com.nicolasmilliard.rxtask;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.MaybeObserver;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

final class MaybeTaskCallback<T> extends TaskDisposable implements
        OnCompleteListener<T> {

    private final MaybeObserver<? super T> observer;

    public MaybeTaskCallback(Task<?> task, MaybeObserver<? super T> observer) {
        super(task);
        this.observer = observer;
    }

    @Override
    public void onComplete(@NonNull Task<T> task) {
        if (isDisposed()) return;
        if (task.isSuccessful()) {
            T result = task.getResult();
            if (result == null) {
                observer.onComplete();
            } else {
                observer.onSuccess(result);
            }
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