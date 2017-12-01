package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.ConfigClient;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class ReadDataTypeSingle extends SingleTask<DataType> {

    final ConfigClient client;
    final String dataTypeName;

    public ReadDataTypeSingle(ConfigClient client, String dataTypeName) {
        this.client = client;
        this.dataTypeName = dataTypeName;
    }

    @Override
    protected Task<DataType> run() {
        return client.readDataType(dataTypeName);
    }
}
