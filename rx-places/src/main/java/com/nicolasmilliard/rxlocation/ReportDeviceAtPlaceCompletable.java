package com.nicolasmilliard.rxlocation;

import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class ReportDeviceAtPlaceCompletable extends CompletableTask {

    final PlaceDetectionClient client;
    final PlaceReport report;

    public ReportDeviceAtPlaceCompletable(PlaceDetectionClient client, PlaceReport report) {
        this.client = client;
        this.report = report;
    }

    @Override
    protected Task<Void> run() {
        return client.reportDeviceAtPlace(report);
    }
}
