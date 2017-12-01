package com.nicolasmilliard.rxfit;

import android.annotation.SuppressLint;
import android.app.PendingIntent;

import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class UnRegisterDataUpdateListenerCompletable extends CompletableTask {

    final HistoryClient client;
    final PendingIntent request;

    public UnRegisterDataUpdateListenerCompletable(HistoryClient client, PendingIntent request) {
        this.client = client;
        this.request = request;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.unregisterDataUpdateListener(request);
    }
}
