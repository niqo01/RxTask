package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.ObservableTask;
import com.nicolasmilliard.rxtask.ObservableTaskCallback;

final class LocationResultObservable extends ObservableTask<LocationResult> {
    private final FusedLocationProviderClient client;
    private final LocationRequest request;

    public LocationResultObservable(FusedLocationProviderClient client, LocationRequest request) {
        this.client = client;
        this.request = request;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run(ObservableTaskCallback<LocationResult> callback) {
        ResultCallback resultCallback = new ResultCallback(callback);
        callback.setDisposeListener(() -> client.removeLocationUpdates(resultCallback));
        return client.requestLocationUpdates(this.request, resultCallback, null)
                .addOnCompleteListener(callback);
    }

    static final class ResultCallback extends LocationCallback {

        final ObservableTaskCallback<LocationResult> callback;

        public ResultCallback(ObservableTaskCallback<LocationResult> callback) {
            this.callback = callback;
        }

        @Override
        public void onLocationResult(LocationResult result) {
            super.onLocationResult(result);
            callback.onNext(result);
        }
    }
}
