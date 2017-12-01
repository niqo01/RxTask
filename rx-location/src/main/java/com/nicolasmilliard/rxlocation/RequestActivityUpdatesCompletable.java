package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;
import android.app.PendingIntent;

import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class RequestActivityUpdatesCompletable extends CompletableTask {

    final ActivityRecognitionClient client;
    final Long detectionIntervalMillis;
    final PendingIntent callbackIntent;

    public RequestActivityUpdatesCompletable(ActivityRecognitionClient client, Long detectionIntervalMillis, PendingIntent
            callbackIntent) {
        this.client = client;
        this.detectionIntervalMillis = detectionIntervalMillis;
        this.callbackIntent = callbackIntent;


    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.requestActivityUpdates(detectionIntervalMillis, callbackIntent);
    }
}
