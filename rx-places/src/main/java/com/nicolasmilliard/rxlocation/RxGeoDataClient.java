package com.nicolasmilliard.rxlocation;

import android.support.annotation.NonNull;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.maps.model.LatLngBounds;

import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.RxTask.single;

public class RxGeoDataClient {

    private final GeoDataClient client;

    public RxGeoDataClient(GeoDataClient client) {
        this.client = client;
    }

    @NonNull
    public Single<AutocompletePredictionBufferResponse> getAutocompletePredictions(String query,
                                                                                   LatLngBounds
                                                                                           bounds, AutocompleteFilter filter) {
        return single(client.getAutocompletePredictions(query, bounds, filter));
    }

    @NonNull
    public Single<PlacePhotoResponse> getPhoto(PlacePhotoMetadata photoMetadata) {
        return single(client.getPhoto(photoMetadata));
    }

    @NonNull
    public Single<PlaceBufferResponse> getPlaceById(String... placeIds) {
        return single(client.getPlaceById(placeIds));
    }

    @NonNull
    public Single<PlacePhotoMetadataResponse> getPlacePhotos(String placeId) {
        return single(client.getPlacePhotos(placeId));
    }

    @NonNull
    public Single<PlacePhotoResponse> getScaledPhoto(PlacePhotoMetadata photoMetadata, int width,
                                                     int height) {
        return single(client.getScaledPhoto(photoMetadata, width, height));
    }

}
