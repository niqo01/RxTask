package com.nicolasmilliard.rxlocation;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class GetPlaceByIdSingle extends SingleTask<PlaceBufferResponse> {

    final GeoDataClient client;
    final String[] placeIds;


    public GetPlaceByIdSingle(GeoDataClient client, String... placeIds) {
        this.client = client;
        this.placeIds = placeIds;
    }

    @Override
    protected Task<PlaceBufferResponse> run() {
        return client.getPlaceById(placeIds);
    }
}
