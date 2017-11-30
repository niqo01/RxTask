package com.nicolasmilliard.rxlocation

import android.support.annotation.RequiresPermission
import com.google.android.gms.location.places.*
import com.google.android.gms.maps.model.LatLngBounds
import com.nicolasmilliard.rxtask.toCompletable
import com.nicolasmilliard.rxtask.toSingle
import io.reactivex.Completable
import io.reactivex.Single

fun GeoDataClient.getAutocompletePredictionsObs(query: String?, bounds: LatLngBounds?, filter: AutocompleteFilter?): Single<AutocompletePredictionBufferResponse> = this.getAutocompletePredictions(query, bounds, filter).toSingle()
fun GeoDataClient.getPhotoObs(photoMetadata: PlacePhotoMetadata): Single<PlacePhotoResponse> = this.getPhoto(photoMetadata).toSingle()
fun GeoDataClient.getPlaceByIdObs(placeIds: Array<String>): Single<PlaceBufferResponse> = this.getPlaceById(*placeIds).toSingle()
fun GeoDataClient.getPlacePhotosObs(placeId: String): Single<PlacePhotoMetadataResponse> = this.getPlacePhotos(placeId).toSingle()
fun GeoDataClient.getScaledPhotoObs(photoMetadata: PlacePhotoMetadata, width: Int, height: Int): Single<PlacePhotoResponse> = this.getScaledPhoto(photoMetadata, width, height).toSingle()

@RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
fun PlaceDetectionClient.getCurrentPlaceObs(filter: PlaceFilter?): Single<PlaceLikelihoodBufferResponse> = this.getCurrentPlace(filter).toSingle()

fun PlaceDetectionClient.reportDeviceAtPlaceObs(report: PlaceReport): Completable = this.reportDeviceAtPlace(report).toCompletable()