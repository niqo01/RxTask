package com.nicolasmilliard.rxlocation;

import android.app.PendingIntent;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class RemoveGeofencesByIntentCompletable extends CompletableTask {

    final GeofencingClient client;
    final PendingIntent pendingIntent;

    public RemoveGeofencesByIntentCompletable(GeofencingClient client, PendingIntent pendingIntent) {
        this.client = client;
        this.pendingIntent = pendingIntent;
    }

    @Override
    protected Task<Void> run() {
        return client.removeGeofences(pendingIntent);
    }
}
