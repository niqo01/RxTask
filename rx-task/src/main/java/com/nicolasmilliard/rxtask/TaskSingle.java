package com.nicolasmilliard.rxtask;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

final class TaskSingle<T> extends Single<T> {
    private final Task<T> task;

    public TaskSingle(Task<T> task) {
        this.task = task;
    }

    protected void subscribeActual(SingleObserver observer) {
        TaskSingle.TaskCallback callback = new TaskSingle.TaskCallback(observer);
        observer.onSubscribe(callback);
        this.task.addOnCompleteListener(callback);
    }

    private static final class TaskCallback<T> implements Disposable, OnCompleteListener<T> {
        private final SingleObserver<T> observer;
        private boolean disposed;

        public TaskCallback(SingleObserver<T> observer) {
            this.observer = observer;
        }

        public void onComplete(Task<T> task) {
            if (this.disposed) return;

            if (task.isSuccessful()) {
                this.observer.onSuccess(task.getResult());
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
