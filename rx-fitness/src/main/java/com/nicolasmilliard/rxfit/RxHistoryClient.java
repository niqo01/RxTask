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

public class RxHistoryClient {

    private final HistoryClient client;

    public RxHistoryClient(HistoryClient client) {
        this.client = client;
    }

    @NonNull
    public Single<DataReadResponse> readData(DataReadRequest request) {
        return new ReadDataSingle(client, request);
    }

    @NonNull
    public Single<DataSet> readDailyTotal(DataType request) {
        return new ReadDailyTotalSingle(client, request);
    }

    @NonNull
    public Single<DataSet> readDailyTotalFromLocalDevice(DataType request) {
        return new ReadDailyTotalFromLocalDeviceSingle(client, request);
    }

    @NonNull
    public Completable registerDataUpdateListener(DataUpdateListenerRegistrationRequest request) {
        return new RegisterDataUpdateListenerCompletable(client, request);
    }

    @NonNull
    public Completable unregisterDataUpdateListener(PendingIntent request) {
        return new UnRegisterDataUpdateListenerCompletable(client, request);
    }

    @NonNull
    public Completable insertData(DataSet dataSet) {
        return new InsertDataCompletable(client, dataSet);
    }

    @NonNull
    public Completable updateData(DataUpdateRequest request) {
        return new UpdateDataCompletable(client, request);
    }

    @NonNull
    public Completable deleteData(DataDeleteRequest request) {
        return new DeleteDataCompletable(client, request);
    }
}
