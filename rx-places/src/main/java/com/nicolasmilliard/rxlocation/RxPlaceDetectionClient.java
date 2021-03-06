package com.nicolasmilliard.rxlocation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;
import com.nicolasmilliard.rxtask.SingleTask;
import com.nicolasmilliard.rxtask.TaskSupplier;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RxPlaceDetectionClient {

    private final PlaceDetectionClient client;

    public RxPlaceDetectionClient(@NonNull Context context, @Nullable PlacesOptions options) {
        this.client = Places.getPlaceDetectionClient(context, options);
    }

    @NonNull
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public Single<PlaceLikelihoodBufferResponse> getCurrentPlace(@Nullable PlaceFilter filter) {
        return SingleTask.create(() -> client.getCurrentPlace(filter));
    }

    @NonNull
    public Completable reportDeviceAtPlace(PlaceReport report) {
        return CompletableTask.create(() -> client.reportDeviceAtPlace(report));
    }
}
