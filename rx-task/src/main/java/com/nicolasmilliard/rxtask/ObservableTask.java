package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;

public abstract class ObservableTask<T> extends Observable<T> {

    ObservableTaskCallback<T> callback;

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        callback = new ObservableTaskCallback(observer);
        observer.onSubscribe(callback);

        Task<Void> task = run(callback);
        task.addOnCompleteListener(callback);
    }

    protected abstract Task<Void> run(ObservableTaskCallback<T> callback);
}
