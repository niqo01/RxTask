package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class ReadDailyTotalFromLocalDeviceSingle extends SingleTask<DataSet> {

    final HistoryClient client;
    final DataType request;

    public ReadDailyTotalFromLocalDeviceSingle(HistoryClient client, DataType request) {
        this.client = client;
        this.request = request;
    }

    @Override
    protected Task<DataSet> run() {
        return client.readDailyTotalFromLocalDevice(request);
    }
}
