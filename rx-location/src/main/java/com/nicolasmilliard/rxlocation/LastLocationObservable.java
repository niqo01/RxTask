package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.MaybeTask;


final class LastLocationObservable extends MaybeTask<Location> {

    private final FusedLocationProviderClient client;

    public LastLocationObservable(FusedLocationProviderClient client) {
        this.client = client;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Location> run() {
        return client.getLastLocation();
    }
}
