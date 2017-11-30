package com.nicolasmilliard.rxtask;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

final class TaskMaybe<T> extends Maybe<T> {
    private final Task<T> task;

    public TaskMaybe(Task<T> task) {
        this.task = task;
    }

    @Override
    protected void subscribeActual(MaybeObserver<? super T> observer) {
        TaskMaybe.TaskCallback callback = new TaskMaybe.TaskCallback(observer);
        observer.onSubscribe(callback);
        this.task.addOnCompleteListener(callback);
    }

    private static final class TaskCallback<T> implements Disposable, OnCompleteListener<T> {
        private final MaybeObserver<T> observer;
        private boolean disposed;

        public TaskCallback(MaybeObserver<T> observer) {
            this.observer = observer;
        }

        public void onComplete(Task<T> task) {
            if (this.disposed) return;

            if (task.isSuccessful()) {
                T result = task.getResult();
                if (result == null) {
                    this.observer.onComplete();
                } else {
                    this.observer.onSuccess(result);
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

        public boolean isDisposed() {
            return this.disposed;
        }

        public void dispose() {
            this.disposed = true;
        }
    }
}
