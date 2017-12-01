package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.BleClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class ClaimBleDeviceByAddressCompletable extends CompletableTask {

    final BleClient client;
    final String deviceAddress;

    public ClaimBleDeviceByAddressCompletable(BleClient client, String deviceAddress) {
        this.client = client;
        this.deviceAddress = deviceAddress;
    }

    @Override
    protected Task<Void> run() {
        return client.claimBleDevice(deviceAddress);
    }
}
