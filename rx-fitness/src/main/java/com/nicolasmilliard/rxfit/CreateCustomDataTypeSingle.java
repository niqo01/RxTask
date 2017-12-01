package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.ConfigClient;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class CreateCustomDataTypeSingle extends SingleTask<DataType> {

    final ConfigClient client;
    final DataTypeCreateRequest request;

    public CreateCustomDataTypeSingle(ConfigClient client, DataTypeCreateRequest request) {
        this.client = client;
        this.request = request;
    }

    @Override
    protected Task<DataType> run() {
        return client.createCustomDataType(request);
    }
}
