package com.nicolasmilliard.rxfit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.BleClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

public class RxBleClient {

    private final BleClient client;

    public RxBleClient(@NonNull Context context, @Nullable GoogleSignInAccount account) {
        checkNotNull(context, "context == null");
        this.client = Fitness.getBleClient(context, account);
    }

    @NonNull
    public Completable claimBleDevice(String deviceAddress) {
        return new ClaimBleDeviceByAddressCompletable(client, deviceAddress);
    }

    @NonNull
    public Completable claimBleDevice(BleDevice bleDevice) {
        return new ClaimBleDeviceCompletable(client, bleDevice);
    }

    @NonNull
    public Single<List<BleDevice>> listClaimedBleDevices() {
        return new ListClaimedBleDeviceSingle(client);
    }

    @NonNull
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public Observable<BleDevice> startBleScan(List<DataType> dataTypes, int timeoutSecs) {
        return new StartBleScanObservable(client, dataTypes, timeoutSecs);
    }

    @NonNull
    public Completable unclaimBleDevice(String deviceAddress) {
        return new UnClaimBleDeviceByAddressCompletable(client, deviceAddress);
    }

    @NonNull
    public Completable unclaimBleDevice(BleDevice bleDevice) {
        return new UnClaimBleDeviceCompletable(client, bleDevice);
    }
}
