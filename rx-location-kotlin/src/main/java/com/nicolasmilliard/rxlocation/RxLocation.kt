package com.nicolasmilliard.rxlocation

import android.app.PendingIntent
import android.location.Location
import android.support.annotation.RequiresPermission
import com.google.android.gms.location.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.getLastLocationObs(): Maybe<Location> = LastLocationObservable(this)

fun FusedLocationProviderClient.flushLocationsObs(): Completable = FlushLocationCompletable(this)
@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.getLocationAvailabilityObs(): Maybe<LocationAvailability> = GetLocationAvailabilityMaybe(this)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.setMockLocationObs(mock: Location): Completable = MockLocationObservable(this, mock)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.setMockModeObs(isMockMode: Boolean): Completable = MockModeObservable(this, isMockMode)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.requestLocationRequestUpdatesObs(request: LocationRequest): Observable<LocationResult> = RequestLocationResultObservable(this, request)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.requestLocationAvailabilityUpdatesObs(request: LocationRequest): Observable<LocationAvailability> = RequestLocationAvailabilityObservable(this, request)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.requestLocationUpdatesObs(request: LocationRequest, callbackIntent: PendingIntent): Completable = RequestLocationUpdateCompletable(this, request, callbackIntent)

fun FusedLocationProviderClient.removeLocationUpdatesObs(callbackIntent: PendingIntent): Completable = RemoveLocationUpdateCompletable(this, callbackIntent)

fun SettingsClient.checkLocationSettingsObs(request: LocationSettingsRequest): Single<LocationSettingsResponse> = CheckLocationSettingsSingle(this, request)

@RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
fun GeofencingClient.addGeofencesObs(request: GeofencingRequest, intent: PendingIntent): Completable = AddGeofencesCompletable(this, request, intent)

fun GeofencingClient.removeGeofencesObs(geofenceRequestIds: List<String>): Completable = RemoveGeofencesCompletable(this, geofenceRequestIds)

fun GeofencingClient.removeGeofencesObs(pendingIntent: PendingIntent): Completable = RemoveGeofencesByIntentCompletable(this, pendingIntent)

@RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
fun ActivityRecognitionClient.requestActivityUpdatesObs(detectionIntervalMillis: Long, callbackIntent: PendingIntent): Completable = RequestActivityUpdatesCompletable(this, detectionIntervalMillis, callbackIntent)

@RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
fun ActivityRecognitionClient.removeActivityUpdatesObs(callbackIntent: PendingIntent): Completable = RemoveActivityUpdatesCompletable(this, callbackIntent)