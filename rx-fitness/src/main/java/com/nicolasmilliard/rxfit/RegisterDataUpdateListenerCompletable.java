package com.nicolasmilliard.rxfit;

import android.annotation.SuppressLint;

import com.google.android.gms.fitness.BleClient;
import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class RegisterDataUpdateListenerCompletable extends CompletableTask {

    final HistoryClient client;
    final DataUpdateListenerRegistrationRequest request;

    public RegisterDataUpdateListenerCompletable(HistoryClient client, DataUpdateListenerRegistrationRequest request) {
        this.client = client;
        this.request = request;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.registerDataUpdateListener(request);
    }
}
