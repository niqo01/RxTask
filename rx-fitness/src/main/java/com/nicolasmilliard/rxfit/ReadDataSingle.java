package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class ReadDataSingle extends SingleTask<DataReadResponse> {

    final HistoryClient client;
    final DataReadRequest request;

    public ReadDataSingle(HistoryClient client, DataReadRequest request) {
        this.client = client;
        this.request = request;
    }

    @Override
    protected Task<DataReadResponse> run() {
        return client.readData(request);
    }
}
