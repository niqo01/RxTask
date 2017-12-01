package com.nicolasmilliard.rxlocation

import android.support.annotation.RequiresPermission
import com.google.android.gms.location.places.*
import com.google.android.gms.maps.model.LatLngBounds
import io.reactivex.Completable
import io.reactivex.Single

fun GeoDataClient.getAutocompletePredictionsObs(query: String?, bounds: LatLngBounds?, filter: AutocompleteFilter?): Single<AutocompletePredictionBufferResponse> = GetAutoCompletePredictionSingle(this, query, bounds, filter)
fun GeoDataClient.getPhotoObs(photoMetadata: PlacePhotoMetadata): Single<PlacePhotoResponse> = GetPhotoSingle(this, photoMetadata)
fun GeoDataClient.getPlaceByIdObs(placeIds: Array<String>): Single<PlaceBufferResponse> = GetPlaceByIdSingle(this, *placeIds)
fun GeoDataClient.getPlacePhotosObs(placeId: String): Single<PlacePhotoMetadataResponse> = GetPlacePhotosSingle(this, placeId)
fun GeoDataClient.getScaledPhotoObs(photoMetadata: PlacePhotoMetadata, width: Int, height: Int): Single<PlacePhotoResponse> = GetScaledPhotoSingle(this, photoMetadata, width, height)

@RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
fun PlaceDetectionClient.getCurrentPlaceObs(filter: PlaceFilter?): Single<PlaceLikelihoodBufferResponse> = GetCurrentPlaceSingle(this, filter)

fun PlaceDetectionClient.reportDeviceAtPlaceObs(report: PlaceReport): Completable = ReportDeviceAtPlaceCompletable(this, report)