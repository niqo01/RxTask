package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.SensorsClient;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

public final class SensorObservable extends Observable<DataPoint> {
    private final SensorsClient client;
    private final SensorRequest request;

    public SensorObservable(SensorsClient client, SensorRequest request) {
        this.client = client;
        this.request = request;
    }

    protected void subscribeActual(Observer observer) {
        SensorObservable.SensorCallback callback = new SensorObservable.SensorCallback(this.client, observer);
        observer.onSubscribe(callback);
        this.client.add(this.request, callback).addOnCompleteListener(callback);
    }

    public static final class SensorCallback implements Disposable, OnCompleteListener<Void>, OnDataPointListener {
        private final SensorsClient client;
        private final Observer<DataPoint> observer;
        private boolean disposed;

        public SensorCallback(SensorsClient client, Observer<DataPoint> observer) {
            this.client = client;
            this.observer = observer;
        }

        public void onDataPoint(DataPoint dataPoint) {
            if (!this.disposed) {
                this.observer.onNext(dataPoint);
            }
        }

        public void onComplete(Task<Void> task) {
            if (this.disposed) return;
            if (!task.isSuccessful()) {
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
            this.client.remove(this).addOnCompleteListener(task -> {

            });
        }
    }
}
