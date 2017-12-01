package com.nicolasmilliard.rxfit;

import android.annotation.SuppressLint;

import com.google.android.gms.fitness.BleClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class UnClaimBleDeviceCompletable extends CompletableTask {

    final BleClient client;
    final BleDevice bleDevice;

    public UnClaimBleDeviceCompletable(BleClient client, BleDevice bleDevice) {
        this.client = client;
        this.bleDevice = bleDevice;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.unclaimBleDevice(bleDevice);
    }
}
