package com.nicolasmilliard.rxlocation;

import android.annotation.SuppressLint;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class GetAutoCompletePredictionSingle extends SingleTask<AutocompletePredictionBufferResponse> {

    final GeoDataClient client;
    final String query;
             final LatLngBounds bounds;
             final AutocompleteFilter filter;

    public GetAutoCompletePredictionSingle(GeoDataClient client, String query, LatLngBounds bounds, AutocompleteFilter filter) {
        this.client = client;
        this.query = query;
        this.bounds = bounds;
        this.filter = filter;
    }

    @Override
    protected Task<AutocompletePredictionBufferResponse> run() {
        return client.getAutocompletePredictions(query, bounds, filter);
    }
}
