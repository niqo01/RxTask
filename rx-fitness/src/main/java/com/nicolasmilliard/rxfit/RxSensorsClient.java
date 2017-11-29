package com.nicolasmilliard.rxfit;

import android.support.annotation.NonNull;

import com.google.android.gms.fitness.SensorsClient;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.SensorRequest;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.RxTask.single;

public class RxSensorsClient {

    private final SensorsClient client;

    public RxSensorsClient(SensorsClient client) {
        this.client = client;
    }

    @NonNull
    public Observable<DataPoint> readData(SensorRequest request) {
        return new SensorObservable(client, request);
    }

    @NonNull
    public Single<List<DataSource>> findDataSources(DataSourcesRequest request) {
        return single(client.findDataSources(request));
    }
}
