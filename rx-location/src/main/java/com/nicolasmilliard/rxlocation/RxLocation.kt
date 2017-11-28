package com.nicolasmilliard.rxlocation

import android.app.PendingIntent
import android.location.Location
import android.support.annotation.RequiresPermission
import com.google.android.gms.location.*
import com.nicolasmilliard.rxtask.toCompletable
import com.nicolasmilliard.rxtask.toMaybe
import com.nicolasmilliard.rxtask.toSingle
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.getLastLocationObs(): Maybe<Location> = this.lastLocation.toMaybe()

fun FusedLocationProviderClient.flushLocationsObs(): Completable = this.flushLocations().toCompletable()
@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.getLocationAvailabilityObs(): Maybe<LocationAvailability> = this.locationAvailability.toMaybe()

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.setMockLocationObs(mock: Location): Completable = this.setMockLocation(mock).toCompletable()

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.setMockModeObs(isMockMode: Boolean): Completable = this.setMockMode(isMockMode).toCompletable()

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.requestLocationRequestUpdatesObs(request: LocationRequest): Observable<LocationResult> = LocationResultObservable(this, request)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.requestLocationAvailabilityUpdatesObs(request: LocationRequest): Observable<LocationAvailability> = LocationAvailabilityObservable(this, request)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.requestLocationUpdatesObs(request: LocationRequest, callbackIntent: PendingIntent): Completable = this.requestLocationUpdates(request, callbackIntent).toCompletable()

fun FusedLocationProviderClient.requestLocationUpdatesObs(callbackIntent: PendingIntent): Completable = this.removeLocationUpdates(callbackIntent).toCompletable()

fun SettingsClient.checkLocationSettingsObs(request: LocationSettingsRequest): Single<LocationSettingsResponse> = this.checkLocationSettings(request).toSingle()

@RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
fun GeofencingClient.addGeofencesObs(request: GeofencingRequest, intent: PendingIntent): Completable = this.addGeofences(request, intent).toCompletable()

fun GeofencingClient.removeGeofencesObs(geofenceRequestIds: List<String>): Completable = this.removeGeofences(geofenceRequestIds).toCompletable()

fun GeofencingClient.removeGeofencesObs(pendingIntent: PendingIntent): Completable = this.removeGeofences(pendingIntent).toCompletable()

@RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
fun ActivityRecognitionClient.requestActivityUpdatesObs(detectionIntervalMillis: Long, callbackIntent: PendingIntent): Completable = this.requestActivityUpdates(detectionIntervalMillis, callbackIntent).toCompletable()

@RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
fun ActivityRecognitionClient.requestActivityUpdatesObs(callbackIntent: PendingIntent): Completable = this.removeActivityUpdates(callbackIntent).toCompletable()