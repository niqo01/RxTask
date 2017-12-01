package com.nicolasmilliard.rxfit;

import android.annotation.SuppressLint;

import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class DeleteDataCompletable extends CompletableTask {

    final HistoryClient client;
    final DataDeleteRequest request;

    public DeleteDataCompletable(HistoryClient client, DataDeleteRequest request) {
        this.client = client;
        this.request = request;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.deleteData(request);
    }
}
