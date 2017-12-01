package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.BleClient;
import com.google.android.gms.fitness.SensorsClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;

import java.util.List;


final class ListClaimedBleDeviceSingle extends SingleTask<List<BleDevice>> {

    final BleClient client;

    public ListClaimedBleDeviceSingle(BleClient client) {
        this.client = client;
    }

    @Override
    protected Task<List<BleDevice>> run() {
        return client.listClaimedBleDevices();
    }
}
