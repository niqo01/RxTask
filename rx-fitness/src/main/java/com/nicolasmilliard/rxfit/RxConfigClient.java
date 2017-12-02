package com.nicolasmilliard.rxfit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.ConfigClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

public class RxConfigClient {

    private final ConfigClient client;

    public RxConfigClient(@NonNull Context context, @Nullable GoogleSignInAccount account) {
        checkNotNull(context, "context == null");
        this.client = Fitness.getConfigClient(context, account);
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