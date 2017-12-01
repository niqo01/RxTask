package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.ObservableTask;
import com.nicolasmilliard.rxtask.ObservableTaskCallback;

final class RequestLocationAvailabilityObservable extends ObservableTask<LocationAvailability> {
    private final FusedLocationProviderClient client;
    private final LocationRequest request;

    public RequestLocationAvailabilityObservable(FusedLocationProviderClient client, LocationRequest
            request) {
        this.client = client;
        this.request = request;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run(ObservableTaskCallback<LocationAvailability> callback) {
        ResultCallback resultCallback = new ResultCallback(callback);
        callback.setDisposeListener(() -> client.removeLocationUpdates(resultCallback));
        return client.requestLocationUpdates(this.request, resultCallback, null);
    }

    static final class ResultCallback extends LocationCallback {

        final ObservableTaskCallback<LocationAvailability> callback;

        public ResultCallback(ObservableTaskCallback<LocationAvailability> callback) {
            this.callback = callback;
        }

        public void onLocationAvailability(LocationAvailability result) {
            super.onLocationAvailability(result);
            callback.onNext(result);
        }
    }
}
