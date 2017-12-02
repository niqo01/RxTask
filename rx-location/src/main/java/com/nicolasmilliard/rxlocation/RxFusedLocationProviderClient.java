package com.nicolasmilliard.rxlocation;


import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

/**
 * FusedLocationProviderClient wrapper {@linkplain Observable observables}.
 */
public class RxFusedLocationProviderClient {

    private final FusedLocationProviderClient client;

    public RxFusedLocationProviderClient(@NonNull Context context) {
        checkNotNull(context, "context == null");
        this.client = LocationServices.getFusedLocationProviderClient(context);
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission" +
                    ".ACCESS_FINE_LOCATION"}
    )
    public Maybe<Location> getLastLocation() {
        return new LastLocationObservable(client);
    }

    @NonNull
    public Completable flushLocations() {
        return new FlushLocationCompletable(client);
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission" +
                    ".ACCESS_FINE_LOCATION"}
    )
    public Maybe<LocationAvailability> getLocationAvailability() {
        return new GetLocationAvailabilityMaybe(client);
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission" +
                    ".ACCESS_FINE_LOCATION"}
    )
    public Completable setMockLocation(Location location) {
        return new MockLocationObservable(client, location);
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission" +
                    ".ACCESS_FINE_LOCATION"}
    )
    public Completable setMockMode(boolean isMockMode) {
        return new MockModeObservable(client, isMockMode);
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission" +
                    ".ACCESS_FINE_LOCATION"}
    )
    public Observable<LocationResult> requestLocationRequestUpdates(LocationRequest request) {
        return new RequestLocationResultObservable(client, request);
    }

    @NonNull
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission" +
                    ".ACCESS_FINE_LOCATION"}
    )
    public Observable<LocationAvailability> requestLocationAvailabilityUpdates(LocationRequest
                                                                                       request) {
        return new RequestLocationAvailabilityObservable(client, request);
    }

    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission" +
                    ".ACCESS_FINE_LOCATION"}
    )
    public Completable requestLocationUpdates(LocationRequest request, PendingIntent
            callbackIntent) {
        return new RequestLocationUpdateCompletable(client, request, callbackIntent);
    }

    public Completable removeLocationUpdates(PendingIntent callbackIntent) {
        return new RemoveLocationUpdateCompletable(client, callbackIntent);
    }
}
