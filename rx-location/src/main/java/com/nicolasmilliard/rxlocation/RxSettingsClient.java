package com.nicolasmilliard.rxlocation;


import android.support.annotation.NonNull;

import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;

import io.reactivex.Observable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.RxTask.single;
import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

/**
 * FusedLocationProviderClient wrapper {@linkplain Observable observables}.
 */
public class RxSettingsClient {

    private final SettingsClient client;

    public RxSettingsClient(@NonNull SettingsClient client) {
        checkNotNull(client, "client == null");
        this.client = client;
    }

    @NonNull
    public Single<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest request) {
        return single(client.checkLocationSettings(request));
    }
}
