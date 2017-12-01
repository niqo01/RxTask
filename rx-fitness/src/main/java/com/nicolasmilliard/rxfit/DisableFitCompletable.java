package com.nicolasmilliard.rxfit;

import android.annotation.SuppressLint;

import com.google.android.gms.fitness.ConfigClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class DisableFitCompletable extends CompletableTask {

    final ConfigClient client;

    public DisableFitCompletable(ConfigClient client) {
        this.client = client;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.disableFit();
    }
}
