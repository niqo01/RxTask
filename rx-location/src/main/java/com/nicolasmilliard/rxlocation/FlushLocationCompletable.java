package com.nicolasmilliard.rxlocation;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class FlushLocationCompletable extends CompletableTask {

    final FusedLocationProviderClient client;

    public FlushLocationCompletable(FusedLocationProviderClient client) {
        this.client = client;
    }

    @Override
    protected Task<Void> run() {
        return client.flushLocations();
    }
}
