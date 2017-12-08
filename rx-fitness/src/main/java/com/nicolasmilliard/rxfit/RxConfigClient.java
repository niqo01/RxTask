package com.nicolasmilliard.rxfit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.ConfigClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.nicolasmilliard.rxtask.CompletableTask;
import com.nicolasmilliard.rxtask.SingleTask;

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
        return SingleTask.fromPlayTask(() -> client.createCustomDataType(request));
    }

    @NonNull
    public Completable disableFit() {
        return CompletableTask.fromPlayTask(() -> client.disableFit());
    }

    @NonNull
    public Single<DataType> readDataType(String dataTypeName) {
        return SingleTask.fromPlayTask(() -> client.readDataType(dataTypeName));
    }
}