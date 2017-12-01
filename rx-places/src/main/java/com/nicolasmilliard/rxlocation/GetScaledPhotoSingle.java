package com.nicolasmilliard.rxlocation;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class GetScaledPhotoSingle extends SingleTask<PlacePhotoResponse> {

    final GeoDataClient client;
    final PlacePhotoMetadata photoMetadata;
    final int width;
    final int height;


    public GetScaledPhotoSingle(GeoDataClient client, PlacePhotoMetadata photoMetadata, int
            width, int height) {
        this.client = client;
        this.photoMetadata = photoMetadata;
        this.width = width;
        this.height = height;
    }

    @Override
    protected Task<PlacePhotoResponse> run() {
        return client.getScaledPhoto(photoMetadata, width, height);
    }
}
