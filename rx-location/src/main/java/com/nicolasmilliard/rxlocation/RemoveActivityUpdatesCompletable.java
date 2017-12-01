package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;
import android.app.PendingIntent;

import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class RemoveActivityUpdatesCompletable extends CompletableTask {

    final ActivityRecognitionClient client;
    final PendingIntent callbackIntent;

    public RemoveActivityUpdatesCompletable(ActivityRecognitionClient client, PendingIntent
            callbackIntent) {
        this.client = client;
        this.callbackIntent = callbackIntent;


    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.removeActivityUpdates(callbackIntent);
    }
}
