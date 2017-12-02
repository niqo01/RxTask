@file:Suppress(
        names = "NOTHING_TO_INLINE"
)
package com.nicolasmilliard.rxlocation

import android.support.annotation.RequiresPermission
import com.google.android.gms.location.places.*
import com.google.android.gms.maps.model.LatLngBounds
import io.reactivex.Completable
import io.reactivex.Single

inline fun GeoDataClient.getAutocompletePredictionsObs(query: String?, bounds: LatLngBounds?, filter: AutocompleteFilter?): Single<AutocompletePredictionBufferResponse>
        = RxGeoDataClient(this).getAutocompletePredictions(query, bounds, filter)

inline fun GeoDataClient.getPhotoObs(photoMetadata: PlacePhotoMetadata): Single<PlacePhotoResponse>
        = RxGeoDataClient(this).getPhoto(photoMetadata)

inline fun GeoDataClient.getPlaceByIdObs(placeIds: Array<String>): Single<PlaceBufferResponse>
        = RxGeoDataClient(this).getPlaceById(*placeIds)

inline fun GeoDataClient.getPlacePhotosObs(placeId: String): Single<PlacePhotoMetadataResponse>
        = RxGeoDataClient(this).getPlacePhotos(placeId)

inline fun GeoDataClient.getScaledPhotoObs(photoMetadata: PlacePhotoMetadata, width: Int, height: Int): Single<PlacePhotoResponse>
        = RxGeoDataClient(this).getScaledPhoto(photoMetadata, width, height)

@RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
inline fun PlaceDetectionClient.getCurrentPlaceObs(filter: PlaceFilter?): Single<PlaceLikelihoodBufferResponse>
        = RxPlaceDetectionClient(this).getCurrentPlace(filter)

inline fun PlaceDetectionClient.reportDeviceAtPlaceObs(report: PlaceReport): Completable
        = RxPlaceDetectionClient(this).reportDeviceAtPlace(report)