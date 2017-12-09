package com.nicolasmilliard.rxlocation;


import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionClient;
import com.nicolasmilliard.rxtask.CompletableTask;

import io.reactivex.Completable;
import io.reactivex.Observable;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

/**
 * FusedLocationProviderClient wrapper {@linkplain Observable observables}.
 */
public class RxActivityRecognitionClient {

    private final ActivityRecognitionClient client;

    public RxActivityRecognitionClient(@NonNull Context context) {
        checkNotNull(context, "context == null");
        this.client = ActivityRecognition.getClient(context);
    }

    @NonNull
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Completable requestActivityUpdates(Long detectionIntervalMillis, PendingIntent
            callbackIntent) {
        return CompletableTask.create(() -> client.requestActivityUpdates(detectionIntervalMillis, callbackIntent));
    }

    @NonNull
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Completable removeActivityUpdates(PendingIntent callbackIntent) {
        return CompletableTask.create(() -> client.removeActivityUpdates(callbackIntent));
    }
}
