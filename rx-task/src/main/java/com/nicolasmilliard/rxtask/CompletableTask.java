package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;

public abstract class CompletableTask extends Completable {
    @Override
    protected void subscribeActual(CompletableObserver observer) {
        Task<Void> task = run();
        CompletableTaskCallback callback = new CompletableTaskCallback(task, observer);
        observer.onSubscribe(callback);
        task.addOnCompleteListener(callback);
    }

    protected abstract Task<Void> run();
}
