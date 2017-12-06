package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;

public abstract class ObservableTask<T> extends Observable<T> {

    public static <S> ObservableTask<S> create(ObservableTaskSupplier<S> taskSupplier){
        return new ObservableTask<S>() {
            @Override
            protected Task<Void> run(ObservableTaskCallback<S> callback) {
                return taskSupplier.get(callback);
            }
        };
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        ObservableTaskCallback<T> callback = new ObservableTaskCallback(observer);
        observer.onSubscribe(callback);

        Task<Void> task = run(callback);
        task.addOnCompleteListener(callback);
    }

    protected abstract Task<Void> run(ObservableTaskCallback<T> callback);
}
