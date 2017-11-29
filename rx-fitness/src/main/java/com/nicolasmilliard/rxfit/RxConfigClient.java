package com.nicolasmilliard.rxfit;

import android.support.annotation.NonNull;

import com.google.android.gms.fitness.ConfigClient;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.RxTask.completable;
import static com.nicolasmilliard.rxtask.RxTask.single;

public class RxConfigClient {

    private final ConfigClient client;

    public RxConfigClient(ConfigClient client) {
        this.client = client;
    }

    @NonNull
    public Single<DataType> createCustomDataType(DataTypeCreateRequest request) {
        return single(client.createCustomDataType(request));
    }

    @NonNull
    public Completable disableFit() {
        return completable(client.disableFit());
    }

    @NonNull
    public Single<DataType> readDataType(String dataTypeName) {
        return single(client.readDataType(dataTypeName));
    }
}