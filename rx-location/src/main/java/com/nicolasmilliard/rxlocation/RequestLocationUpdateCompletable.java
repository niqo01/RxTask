package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;
import android.app.PendingIntent;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class RequestLocationUpdateCompletable extends CompletableTask {

    final FusedLocationProviderClient client;
    final LocationRequest request;
    final PendingIntent callbackIntent;

    public RequestLocationUpdateCompletable(FusedLocationProviderClient client, LocationRequest
            request, PendingIntent callbackIntent) {
        this.client = client;
        this.request = request;
        this.callbackIntent = callbackIntent;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.requestLocationUpdates(request, callbackIntent);
    }
}
