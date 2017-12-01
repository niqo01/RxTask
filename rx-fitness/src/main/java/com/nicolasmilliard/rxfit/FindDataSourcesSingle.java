package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.SensorsClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;

import java.util.List;


final class FindDataSourcesSingle extends SingleTask<List<DataSource>> {

    final SensorsClient client;
    final DataSourcesRequest request;

    public FindDataSourcesSingle(SensorsClient client, DataSourcesRequest request) {
        this.client = client;
        this.request = request;
    }

    @Override
    protected Task<List<DataSource>> run() {
        return client.findDataSources(request);
    }
}
