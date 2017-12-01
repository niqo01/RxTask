package com.nicolasmilliard.rxfit;

import android.annotation.SuppressLint;

import com.google.android.gms.fitness.BleClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class ClaimBleDeviceCompletable extends CompletableTask {

    final BleClient client;
    final BleDevice bleDevice;

    public ClaimBleDeviceCompletable(BleClient client, BleDevice bleDevice) {
        this.client = client;
        this.bleDevice = bleDevice;
    }

    @Override
    protected Task<Void> run() {
        return client.claimBleDevice(bleDevice);
    }
}
