package com.nicolasmilliard.rxlocation;


import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.nicolasmilliard.rxtask.SingleTask;

import io.reactivex.Observable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

/**
 * FusedLocationProviderClient wrapper {@linkplain Observable observables}.
 */
public class RxSettingsClient {

    private final SettingsClient client;

    public RxSettingsClient(@NonNull Context context) {
        checkNotNull(context, "context == null");
        this.client = LocationServices.getSettingsClient(context);
    }

    @NonNull
    public Single<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest request) {
        return SingleTask.fromPlayTask(() -> client.checkLocationSettings(request));
    }
}
