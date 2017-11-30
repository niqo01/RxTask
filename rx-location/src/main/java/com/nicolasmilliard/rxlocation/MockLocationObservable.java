package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class MockLocationObservable extends CompletableTask {

    final FusedLocationProviderClient client;
    final Location location;

    public MockLocationObservable(FusedLocationProviderClient client, Location location) {
        this.client = client;
        this.location = location;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.setMockLocation(location);
    }
}
