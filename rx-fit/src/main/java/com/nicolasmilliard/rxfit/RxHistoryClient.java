package com.nicolasmilliard.rxfit;

import android.app.PendingIntent;
import android.support.annotation.NonNull;

import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DataReadResponse;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.RxTask.toCompletable;
import static com.nicolasmilliard.rxtask.RxTask.toSingle;

public class RxHistoryClient {

    private final HistoryClient client;

    public RxHistoryClient(HistoryClient client) {
        this.client = client;
    }

    @NonNull
    public Single<DataReadResponse> readData(DataReadRequest request) {
        return toSingle(client.readData(request));
    }

    @NonNull
    public Single<DataSet> readDailyTotal(DataType request) {
        return toSingle(client.readDailyTotal(request));
    }

    @NonNull
    public Single<DataSet> readDailyTotalFromLocalDevice(DataType request) {
        return toSingle(client.readDailyTotalFromLocalDevice(request));
    }

    @NonNull
    public Completable registerDataUpdateListener(DataUpdateListenerRegistrationRequest request) {
        return toCompletable(client.registerDataUpdateListener(request));
    }

    @NonNull
    public Completable unregisterDataUpdateListener(PendingIntent request) {
        return toCompletable(client.unregisterDataUpdateListener(request));
    }

    @NonNull
    public Completable insertData(DataSet dataSet) {
        return toCompletable(client.insertData(dataSet));
    }

    @NonNull
    public Completable updateData(DataUpdateRequest request) {
        return toCompletable(client.updateData(request));
    }

    @NonNull
    public Completable deleteData(DataDeleteRequest request) {
        return toCompletable(client.deleteData(request));
    }
}
