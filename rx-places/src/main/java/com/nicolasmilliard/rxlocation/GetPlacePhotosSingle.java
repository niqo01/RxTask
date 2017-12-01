package com.nicolasmilliard.rxlocation;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class GetPlacePhotosSingle extends SingleTask<PlacePhotoMetadataResponse> {

    final GeoDataClient client;
    final String placeId;


    public GetPlacePhotosSingle(GeoDataClient client, String placeId) {
        this.client = client;
        this.placeId = placeId;
    }

    @Override
    protected Task<PlacePhotoMetadataResponse> run() {
        return client.getPlacePhotos(placeId);
    }
}
