package com.nicolasmilliard.rxtask;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.CompletableObserver;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

final class CompletableTaskCallback extends TaskDisposable implements
        OnCompleteListener<Void> {

    private final CompletableObserver observer;

    public CompletableTaskCallback(Task<Void> task, CompletableObserver observer) {
        super(task);
        this.observer = observer;
    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (isDisposed()) return;
        if (task.isSuccessful()) {
            observer.onComplete();
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