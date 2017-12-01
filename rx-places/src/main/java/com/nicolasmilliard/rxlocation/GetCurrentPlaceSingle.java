package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;

import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class GetCurrentPlaceSingle extends SingleTask<PlaceLikelihoodBufferResponse> {

    final PlaceDetectionClient client;
    final PlaceFilter filter;

    public GetCurrentPlaceSingle(PlaceDetectionClient client, PlaceFilter filter) {
        this.client = client;
        this.filter = filter;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<PlaceLikelihoodBufferResponse> run() {
        return client.getCurrentPlace(filter);
    }
}
