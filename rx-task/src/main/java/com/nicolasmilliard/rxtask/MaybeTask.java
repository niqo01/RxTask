package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;

public abstract class MaybeTask<T> extends Maybe<T> {

    public static <S> MaybeTask<S> fromPlayTask(TaskSupplier<Task<S>> taskSupplier){
        return new MaybeTask<S>() {
            @Override
            protected Task<S> run() {
                return taskSupplier.get();
            }
        };
    }

    @Override
    protected void subscribeActual(MaybeObserver<? super T> observer) {
        Task<T> task = run();
        MaybeTaskCallback<T> callback = new MaybeTaskCallback(task, observer);
        observer.onSubscribe(callback);
        task.addOnCompleteListener(callback);
    }

    protected abstract Task<T> run();
}
