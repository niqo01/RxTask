package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.BleClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class UnClaimBleDeviceByAddressCompletable extends CompletableTask {

    final BleClient client;
    final String deviceAddress;

    public UnClaimBleDeviceByAddressCompletable(BleClient client, String deviceAddress) {
        this.client = client;
        this.deviceAddress = deviceAddress;
    }

    @Override
    protected Task<Void> run() {
        return client.unclaimBleDevice(deviceAddress);
    }
}
