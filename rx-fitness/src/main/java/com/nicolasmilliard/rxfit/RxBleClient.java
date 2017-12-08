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
import com.google.android.gms.fitness.request.BleScanCallback;
import com.nicolasmilliard.rxtask.CompletableTask;
import com.nicolasmilliard.rxtask.ObservableTask;
import com.nicolasmilliard.rxtask.SingleTask;

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
        return CompletableTask.fromPlayTask(() -> client.claimBleDevice(deviceAddress));
    }

    @NonNull
    public Completable claimBleDevice(BleDevice bleDevice) {
        return CompletableTask.fromPlayTask(() -> client.claimBleDevice(bleDevice));
    }

    @NonNull
    public Single<List<BleDevice>> listClaimedBleDevices() {
        return SingleTask.fromPlayTask(() -> client.listClaimedBleDevices());
    }

    @NonNull
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public Observable<BleDevice> startBleScan(List<DataType> dataTypes, int timeoutSecs) {
        return ObservableTask.fromPlayTask(callback -> {
            BleScanCallback bleCallBack = new BleScanCallback() {
                @Override
                public void onDeviceFound(BleDevice bleDevice) {
                    callback.onNext(bleDevice);
                }

                @Override
                public void onScanStopped() {
                    callback.onComplete();
                }
            };
            callback.setDisposeListener(() -> client.stopBleScan(bleCallBack));
            return client.startBleScan(dataTypes, timeoutSecs, bleCallBack);
        });
    }

    @NonNull
    public Completable unclaimBleDevice(String deviceAddress) {
        return CompletableTask.fromPlayTask(() -> client.unclaimBleDevice(deviceAddress));
    }

    @NonNull
    public Completable unclaimBleDevice(BleDevice bleDevice) {
        return CompletableTask.fromPlayTask(() -> client.unclaimBleDevice(bleDevice));
    }
}
