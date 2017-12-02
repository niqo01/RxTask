package com.nicolasmilliard.rxfit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.SensorsClient;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.SensorRequest;
import com.nicolasmilliard.rxtask.SingleTask;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

public class RxSensorsClient {

    private final SensorsClient client;

    public RxSensorsClient(@NonNull Context context, @Nullable GoogleSignInAccount account) {
        checkNotNull(context, "context == null");
        this.client = Fitness.getSensorsClient(context, account);
    }

    @NonNull
    public Observable<DataPoint> readData(SensorRequest request) {
        return new SensorObservable(client, request);
    }

    @NonNull
    public Single<List<DataSource>> findDataSources(DataSourcesRequest request) {
        return SingleTask.create(() -> client.findDataSources(request));
    }
}
