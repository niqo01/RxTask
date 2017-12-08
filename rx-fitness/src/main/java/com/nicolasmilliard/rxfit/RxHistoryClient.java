package com.nicolasmilliard.rxfit;

import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.nicolasmilliard.rxtask.CompletableTask;
import com.nicolasmilliard.rxtask.SingleTask;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

public class RxHistoryClient {

    private final HistoryClient client;

    public RxHistoryClient(@NonNull Context context, @Nullable GoogleSignInAccount account) {
        checkNotNull(context, "context == null");
        this.client = Fitness.getHistoryClient(context, account);
    }

    @NonNull
    public Single<DataReadResponse> readData(DataReadRequest request) {
        return SingleTask.fromPlayTask(() -> client.readData(request));
    }

    @NonNull
    public Single<DataSet> readDailyTotal(DataType request) {
        return SingleTask.fromPlayTask(() -> client.readDailyTotal(request));
    }

    @NonNull
    public Single<DataSet> readDailyTotalFromLocalDevice(DataType request) {
        return SingleTask.fromPlayTask(() -> client.readDailyTotalFromLocalDevice(request));
    }

    @NonNull
    public Completable registerDataUpdateListener(DataUpdateListenerRegistrationRequest request) {
        return CompletableTask.fromPlayTask(() -> client.registerDataUpdateListener(request));
    }

    @NonNull
    public Completable unregisterDataUpdateListener(PendingIntent request) {
        return CompletableTask.fromPlayTask(() -> client.unregisterDataUpdateListener(request));
    }

    @NonNull
    public Completable insertData(DataSet dataSet) {
        return CompletableTask.fromPlayTask(() -> client.insertData(dataSet));
    }

    @NonNull
    public Completable updateData(DataUpdateRequest request) {
        return CompletableTask.fromPlayTask(() -> client.updateData(request));
    }

    @NonNull
    public Completable deleteData(DataDeleteRequest request) {
        return CompletableTask.fromPlayTask(() -> client.deleteData(request));
    }
}
