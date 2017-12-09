package com.nicolasmilliard.rxlocation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.nicolasmilliard.rxtask.SingleTask;

import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

public class RxGeoDataClient {

    private final GeoDataClient client;

    public RxGeoDataClient(@NonNull Context context, @Nullable PlacesOptions options) {
        checkNotNull(context, "context == null");
        this.client = Places.getGeoDataClient(context, options);
    }

    @NonNull
    public Single<AutocompletePredictionBufferResponse> getAutocompletePredictions(String query,
                                                                                   LatLngBounds
                                                                                           bounds, AutocompleteFilter filter) {
        return SingleTask.create(() -> client.getAutocompletePredictions(query, bounds, filter));
    }

    @NonNull
    public Single<PlacePhotoResponse> getPhoto(PlacePhotoMetadata photoMetadata) {
        return SingleTask.create(() -> client.getPhoto(photoMetadata));
    }

    @NonNull
    public Single<PlaceBufferResponse> getPlaceById(String... placeIds) {
        return SingleTask.create(() -> client.getPlaceById(placeIds));
    }

    @NonNull
    public Single<PlacePhotoMetadataResponse> getPlacePhotos(String placeId) {
        return SingleTask.create(() -> client.getPlacePhotos(placeId));
    }

    @NonNull
    public Single<PlacePhotoResponse> getScaledPhoto(PlacePhotoMetadata photoMetadata, int width,
                                                     int height) {
        return SingleTask.create(() -> client.getScaledPhoto(photoMetadata, width, height));
    }

}
