package com.nicolasmilliard.rxlocation;

import android.app.PendingIntent;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class RemoveLocationUpdateCompletable extends CompletableTask {

    final FusedLocationProviderClient client;
    final PendingIntent callbackIntent;

    public RemoveLocationUpdateCompletable(FusedLocationProviderClient client, PendingIntent callbackIntent) {
        this.client = client;
        this.callbackIntent = callbackIntent;
    }

    @Override
    protected Task<Void> run() {
        return client.removeLocationUpdates(callbackIntent);
    }
}
