package com.nicolasmilliard.rxfit;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.fitness.BleClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.RxTask.completable;
import static com.nicolasmilliard.rxtask.RxTask.single;

public class RxBleClient {

    private final BleClient client;

    public RxBleClient(BleClient client) {
        this.client = client;
    }

    @NonNull
    public Completable claimBleDevice(String deviceAddress) {
        return completable(client.claimBleDevice(deviceAddress));
    }

    @NonNull
    public Completable claimBleDevice(BleDevice bleDevice) {
        return completable(client.claimBleDevice(bleDevice));
    }

    @NonNull
    public Single<List<BleDevice>> listClaimedBleDevices() {
        return single(client.listClaimedBleDevices());
    }

    @NonNull
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public Completable startBleScan(List<DataType> dataTypes, int timeoutSecs, BleScanCallback callback) {
        return completable(client.startBleScan(dataTypes, timeoutSecs, callback));
    }

    @NonNull
    public Single<Boolean> stopBleScan(BleScanCallback callback) {
        return single(client.stopBleScan(callback));
    }

    @NonNull
    public Completable unclaimBleDevice(String deviceAddress) {
        return completable(client.unclaimBleDevice(deviceAddress));
    }

    @NonNull
    public Completable unclaimBleDevice(BleDevice bleDevice) {
        return completable(client.unclaimBleDevice(bleDevice));
    }
}
