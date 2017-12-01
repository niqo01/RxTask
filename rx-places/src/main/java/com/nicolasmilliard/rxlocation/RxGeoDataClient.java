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

public class RxGeoDataClient {

    private final GeoDataClient client;

    public RxGeoDataClient(GeoDataClient client) {
        this.client = client;
    }

    @NonNull
    public Single<AutocompletePredictionBufferResponse> getAutocompletePredictions(String query,
                                                                                   LatLngBounds
                                                                                           bounds, AutocompleteFilter filter) {
        return new GetAutoCompletePredictionSingle(client, query, bounds, filter);
    }

    @NonNull
    public Single<PlacePhotoResponse> getPhoto(PlacePhotoMetadata photoMetadata) {
        return new GetPhotoSingle(client, photoMetadata);
    }

    @NonNull
    public Single<PlaceBufferResponse> getPlaceById(String... placeIds) {
        return new GetPlaceByIdSingle(client, placeIds);
    }

    @NonNull
    public Single<PlacePhotoMetadataResponse> getPlacePhotos(String placeId) {
        return new GetPlacePhotosSingle(client, placeId);
    }

    @NonNull
    public Single<PlacePhotoResponse> getScaledPhoto(PlacePhotoMetadata photoMetadata, int width,
                                                     int height) {
        return new GetScaledPhotoSingle(client, photoMetadata, width, height);
    }

}
