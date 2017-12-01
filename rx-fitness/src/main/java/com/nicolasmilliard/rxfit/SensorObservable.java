package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.SensorsClient;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.ObservableTask;
import com.nicolasmilliard.rxtask.ObservableTaskCallback;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

final class SensorObservable extends ObservableTask<DataPoint> {

    private final SensorsClient client;
    private final SensorRequest request;

    public SensorObservable(SensorsClient client, SensorRequest request) {
        this.client = client;
        this.request = request;
    }

    @Override
    protected Task<Void> run(ObservableTaskCallback<DataPoint> callback) {
        OnDataPointListener listener = dataPoint -> callback.onNext(dataPoint);
        callback.setDisposeListener(() -> this.client.remove(listener));
        return client.add(this.request, listener)
                .addOnCompleteListener(callback);
    }
}