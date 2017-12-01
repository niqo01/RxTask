package com.nicolasmilliard.rxfit;

import android.annotation.SuppressLint;

import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class InsertDataCompletable extends CompletableTask {

    final HistoryClient client;
    final DataSet dataSet;

    public InsertDataCompletable(HistoryClient client, DataSet dataSet) {
        this.client = client;
        this.dataSet = dataSet;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.insertData(dataSet);
    }
}
