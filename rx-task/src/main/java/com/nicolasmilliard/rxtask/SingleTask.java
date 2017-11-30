package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

public abstract class SingleTask<T> extends Single<T> {
    @Override
    protected void subscribeActual(SingleObserver<? super T> observer) {
        Task<T> task = run();
        SingleTaskCallback<T> callback = new SingleTaskCallback(task, observer);
        observer.onSubscribe(callback);
        task.addOnCompleteListener(callback);
    }

    protected abstract Task<T> run();
}
