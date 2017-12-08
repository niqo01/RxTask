package com.nicolasmilliard.rxlocation;


import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.nicolasmilliard.rxtask.CompletableTask;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

/**
 * FusedLocationProviderClient wrapper {@linkplain Observable observables}.
 */
public class RxGeofencingClient {

    private final GeofencingClient client;

    public RxGeofencingClient(@NonNull Context context) {
        checkNotNull(context, "context == null");
        this.client = LocationServices.getGeofencingClient(context);
    }

    @NonNull
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public Completable addGeofences(GeofencingRequest request, PendingIntent intent) {
        return CompletableTask.fromPlayTask(() -> client.addGeofences(request, intent));
    }

    @NonNull
    public Completable removeGeofences(List<String> geofenceRequestIds) {
        return CompletableTask.fromPlayTask(() -> client.removeGeofences(geofenceRequestIds));
    }

    @NonNull
    public Completable removeGeofences(PendingIntent pendingIntent) {
        return CompletableTask.fromPlayTask(() -> client.removeGeofences(pendingIntent));
    }
}
