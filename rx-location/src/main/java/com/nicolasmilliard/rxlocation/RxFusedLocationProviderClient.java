package com.nicolasmilliard.rxlocation;


import android.app.PendingIntent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

import static com.nicolasmilliard.rxtask.RxTask.toCompletable;
import static com.nicolasmilliard.rxtask.RxTask.toMaybe;
import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

/**
 * FusedLocationProviderClient wrapper {@linkplain Observable observables}.
 */
public class RxFusedLocationProviderClient {

    private final FusedLocationProviderClient client;

    public RxFusedLocationProviderClient(@NonNull FusedLocationProviderClient client) {
        checkNotNull(client, "client == null");
        this.client = client;
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    public Maybe<Location> getLastLocation() {
        return toMaybe(client.getLastLocation());
    }

    @NonNull
    public Completable flushLocations() {
        return toCompletable(client.flushLocations());
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    public Maybe<LocationAvailability> getLocationAvailability() {
        return toMaybe(client.getLocationAvailability());
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    public Completable setMockLocation(Location location) {
        return toCompletable(client.setMockLocation(location));
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    public Completable setMockMode(boolean isMockMode) {
        return toCompletable(client.setMockMode(isMockMode));
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    public Observable<LocationResult> requestLocationRequestUpdates(LocationRequest request) {
        return new LocationResultObservable(client, request);
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    public Observable<LocationAvailability> requestLocationAvailabilityUpdates(LocationRequest request) {
        return new LocationAvailabilityObservable(client, request);
    }

    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    public Completable requestLocationUpdates(LocationRequest request, PendingIntent callbackIntent) {
        return toCompletable(client.requestLocationUpdates(request, callbackIntent));
    }

    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    public Completable removeLocationUpdates(PendingIntent callbackIntent) {
        return toCompletable(client.removeLocationUpdates(callbackIntent));
    }
}
