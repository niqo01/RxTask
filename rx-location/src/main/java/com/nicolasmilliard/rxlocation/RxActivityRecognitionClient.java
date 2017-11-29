package com.nicolasmilliard.rxlocation;


import android.app.PendingIntent;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.location.ActivityRecognitionClient;

import io.reactivex.Completable;
import io.reactivex.Observable;

import static com.nicolasmilliard.rxtask.RxTask.completable;
import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

/**
 * FusedLocationProviderClient wrapper {@linkplain Observable observables}.
 */
public class RxActivityRecognitionClient {

    private final ActivityRecognitionClient client;

    public RxActivityRecognitionClient(@NonNull ActivityRecognitionClient client) {
        checkNotNull(client, "client == null");
        this.client = client;
    }

    @NonNull
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Completable requestActivityUpdates(Long detectionIntervalMillis, PendingIntent callbackIntent) {
        return completable(client.requestActivityUpdates(detectionIntervalMillis, callbackIntent));
    }

    @NonNull
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Completable removeActivityUpdates(PendingIntent callbackIntent) {
        return completable(client.removeActivityUpdates(callbackIntent));
    }
}
