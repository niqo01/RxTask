package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.MaybeTask;


final class GetLocationAvailabilityMaybe extends MaybeTask<LocationAvailability> {

    final FusedLocationProviderClient client;

    public GetLocationAvailabilityMaybe(FusedLocationProviderClient client) {
        this.client = client;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<LocationAvailability> run() {
        return client.getLocationAvailability();
    }
}
