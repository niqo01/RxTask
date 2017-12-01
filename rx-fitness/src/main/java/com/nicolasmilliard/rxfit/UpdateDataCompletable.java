package com.nicolasmilliard.rxfit;

import android.annotation.SuppressLint;

import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class UpdateDataCompletable extends CompletableTask {

    final HistoryClient client;
    final DataUpdateRequest request;

    public UpdateDataCompletable(HistoryClient client, DataUpdateRequest request) {
        this.client = client;
        this.request = request;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.updateData(request);
    }
}
