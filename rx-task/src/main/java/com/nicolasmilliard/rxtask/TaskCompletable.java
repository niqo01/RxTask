package com.nicolasmilliard.rxtask;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

final class TaskCompletable extends Completable {

    private final Task task;

    public TaskCompletable(Task task) {
        this.task = task;
    }

    protected void subscribeActual(CompletableObserver observer) {
        TaskCompletable.TaskCallback callback = new TaskCompletable.TaskCallback(observer);
        observer.onSubscribe(callback);
        this.task.addOnCompleteListener(callback);
    }

    private static final class TaskCallback implements Disposable, OnCompleteListener {
        private final CompletableObserver observer;
        private boolean disposed;

        public TaskCallback(CompletableObserver observer) {
            this.observer = observer;
        }

        public void onComplete(Task task) {
            if (this.disposed) return;

            if (task.isSuccessful()) {
                this.observer.onComplete();
            } else {
                try {
                    observer.onError(task.getException());
                } catch (Throwable t) {
                    Exceptions.throwIfFatal(t);
                    RxJavaPlugins.onError((new CompositeException(task.getException())));
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
