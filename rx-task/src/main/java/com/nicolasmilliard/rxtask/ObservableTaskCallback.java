package com.nicolasmilliard.rxtask;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.Observer;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableTaskCallback<T> extends ObservableDisposable implements
        OnCompleteListener<Void> {

    private final Observer<? super T> observer;
    Dispose listener;
    boolean terminated = false;

    public ObservableTaskCallback(Observer<? super T> observer) {
        this.observer = observer;
    }

    public void setDisposeListener(Dispose listener) {
        this.listener = listener;
    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (isDisposed()) return;
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            if (terminated) {
                RxJavaPlugins.onError(exception);
            } else {
                try {
                    terminated = true;
                    observer.onError(exception);
                } catch (Throwable t) {
                    Exceptions.throwIfFatal(t);
                    RxJavaPlugins.onError(new CompositeException(task.getException(), t));
                }
            }
        }
    }

    public void onComplete() {
        if (isDisposed()) return;
        terminated = true;
        observer.onComplete();
    }

    public void onNext(T value) {
        if (isDisposed()) return;
        if (terminated) {
            throw new ProtocolViolationException("OnNext called after Observable terminal event");
        }
        observer.onNext(value);
    }

    @Override
    protected void onDispose() {
        if (listener != null) {
            listener.onDispose();
        }
    }

    public interface Dispose {
        void onDispose();
    }
}