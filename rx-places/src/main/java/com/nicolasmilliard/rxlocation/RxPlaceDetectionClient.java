package com.nicolasmilliard.rxlocation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.PlaceReport;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.RxTask.completable;
import static com.nicolasmilliard.rxtask.RxTask.single;

public class RxPlaceDetectionClient {

    private final PlaceDetectionClient client;

    public RxPlaceDetectionClient(PlaceDetectionClient client) {
        this.client = client;
    }

    @NonNull
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public Single<PlaceLikelihoodBufferResponse> getCurrentPlace(@Nullable PlaceFilter filter) {
        return single(client.getCurrentPlace(filter));
    }

    @NonNull
    public Completable reportDeviceAtPlace(PlaceReport report) {
        return completable(client.reportDeviceAtPlace(report));
    }
}
