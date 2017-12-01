package com.nicolasmilliard.rxlocation;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;

import java.util.List;


final class RemoveGeofencesCompletable extends CompletableTask {

    final GeofencingClient client;
    final List<String> geofenceRequestIds;

    public RemoveGeofencesCompletable(GeofencingClient client, List<String> geofenceRequestIds) {
        this.client = client;
        this.geofenceRequestIds = geofenceRequestIds;
    }

    @Override
    protected Task<Void> run() {
        return client.removeGeofences(geofenceRequestIds);
    }
}
