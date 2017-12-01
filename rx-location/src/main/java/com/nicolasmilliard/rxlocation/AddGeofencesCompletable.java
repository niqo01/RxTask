package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;
import android.app.PendingIntent;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class AddGeofencesCompletable extends CompletableTask {

    final GeofencingClient client;
    final GeofencingRequest request;
    final PendingIntent intent;

    public AddGeofencesCompletable(GeofencingClient client, GeofencingRequest request,
                                   PendingIntent intent) {
        this.client = client;
        this.request = request;
        this.intent = intent;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.addGeofences(request, intent);
    }
}
