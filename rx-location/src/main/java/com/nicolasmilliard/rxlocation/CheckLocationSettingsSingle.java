package com.nicolasmilliard.rxlocation;

import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class CheckLocationSettingsSingle extends SingleTask<LocationSettingsResponse> {

    final SettingsClient client;
    final LocationSettingsRequest request;

    public CheckLocationSettingsSingle(SettingsClient client, LocationSettingsRequest request) {
        this.client = client;
        this.request = request;
    }

    @Override
    protected Task<LocationSettingsResponse> run() {
        return client.checkLocationSettings(request);
    }
}
