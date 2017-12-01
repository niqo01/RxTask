package com.nicolasmilliard.rxlocation;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class GetPhotoSingle extends SingleTask<PlacePhotoResponse> {

    final GeoDataClient client;
    final PlacePhotoMetadata photoMetadata;


    public GetPhotoSingle(GeoDataClient client, PlacePhotoMetadata photoMetadata) {
        this.client = client;
        this.photoMetadata = photoMetadata;
    }

    @Override
    protected Task<PlacePhotoResponse> run() {
        return client.getPhoto(photoMetadata);
    }
}
