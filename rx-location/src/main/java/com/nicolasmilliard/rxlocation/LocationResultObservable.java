package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

final class LocationResultObservable extends Observable<LocationResult> {
    private final FusedLocationProviderClient client;
    private final LocationRequest request;

    public LocationResultObservable(FusedLocationProviderClient client, LocationRequest request) {
        this.client = client;
        this.request = request;
    }

    @SuppressLint({"MissingPermission"})
    @Override
    protected void subscribeActual(Observer<? super LocationResult> observer) {
        LocationResultObservable.ResultCallback callback = new LocationResultObservable
                .ResultCallback(this.client, observer);
        observer.onSubscribe(callback);
        this.client.requestLocationUpdates(this.request, callback, null).addOnCompleteListener
                (callback);
    }

    public static final class ResultCallback extends LocationCallback implements Disposable,
            OnCompleteListener<Void> {
        private final FusedLocationProviderClient client;
        private final Observer<? super LocationResult> observer;
        private boolean disposed;

        public ResultCallback(FusedLocationProviderClient client, Observer<? super
                LocationResult> observer) {
            this.client = client;
            this.observer = observer;
        }

        public void onLocationResult(LocationResult result) {
            super.onLocationResult(result);
            if (!this.disposed) {
                this.observer.onNext(result);
            }
        }

        public void onComplete(Task<Void> task) {
            if (!this.disposed) {
                if (!task.isSuccessful()) {
                    try {
                        observer.onError(task.getException());
                    } catch (Throwable t) {
                        Exceptions.throwIfFatal(t);
                        RxJavaPlugins.onError(new CompositeException(task.getException(), t));
                    }
                }

            }
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        public void dispose() {
            this.disposed = true;
            this.client.removeLocationUpdates(this).addOnCompleteListener(task -> {

            });
        }
    }
}
