package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class MockModeObservable extends CompletableTask {

    final FusedLocationProviderClient client;
    final boolean mockMode;

    public MockModeObservable(FusedLocationProviderClient client, boolean mockMode) {
        this.client = client;
        this.mockMode = mockMode;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.setMockMode(mockMode);
    }
}
