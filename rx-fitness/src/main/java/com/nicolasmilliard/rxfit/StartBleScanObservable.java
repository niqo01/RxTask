package com.nicolasmilliard.rxfit;

import android.annotation.SuppressLint;

import com.google.android.gms.fitness.BleClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.ObservableTask;
import com.nicolasmilliard.rxtask.ObservableTaskCallback;

import java.util.List;


final class StartBleScanObservable extends ObservableTask<BleDevice> {

    final BleClient client;
    final List<DataType> dataTypes;
    final int timeoutSecs;

    public StartBleScanObservable(BleClient client, List<DataType> dataTypes, int timeoutSecs) {
        this.client = client;
        this.dataTypes = dataTypes;
        this.timeoutSecs = timeoutSecs;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run(ObservableTaskCallback<BleDevice> callback) {
        ResultCallback resultCallback = new ResultCallback(callback);
        callback.setDisposeListener(() -> client.stopBleScan(resultCallback));
        return client.startBleScan(dataTypes, timeoutSecs, resultCallback)
                .addOnCompleteListener(callback);
    }

    static final class ResultCallback extends BleScanCallback {

        final ObservableTaskCallback<BleDevice> callback;

        public ResultCallback(ObservableTaskCallback<BleDevice> callback) {
            this.callback = callback;
        }


        @Override
        public void onDeviceFound(BleDevice bleDevice) {
            callback.onNext(bleDevice);
        }

        @Override
        public void onScanStopped() {
            callback.onComplete();
        }
    }
}
