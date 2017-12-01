package com.nicolasmilliard.rxfit;

import android.support.annotation.NonNull;

import com.google.android.gms.fitness.ConfigClient;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RxConfigClient {

    private final ConfigClient client;

    public RxConfigClient(ConfigClient client) {
        this.client = client;
    }

    @NonNull
    public Single<DataType> createCustomDataType(DataTypeCreateRequest request) {
        return new CreateCustomDataTypeSingle(client, request);
    }

    @NonNull
    public Completable disableFit() {
        return new DisableFitCompletable(client);
    }

    @NonNull
    public Single<DataType> readDataType(String dataTypeName) {
        return new ReadDataTypeSingle(client, dataTypeName);
    }
}