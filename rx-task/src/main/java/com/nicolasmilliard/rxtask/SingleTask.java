package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

public abstract class SingleTask<T> extends Single<T> {

    public static <S> SingleTask<S> create(TaskSupplier<Task<S>> taskSupplier){
        return new SingleTask<S>() {
            @Override
            protected Task<S> run() {
                return taskSupplier.get();
            }
        };
    }

    @Override
    protected void subscribeActual(SingleObserver<? super T> observer) {
        Task<T> task = run();
        SingleTaskCallback<T> callback = new SingleTaskCallback(task, observer);
        observer.onSubscribe(callback);
        task.addOnCompleteListener(callback);
    }

    protected abstract Task<T> run();
}
