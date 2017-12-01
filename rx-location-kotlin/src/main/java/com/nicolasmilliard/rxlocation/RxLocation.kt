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
fun FusedLocationProviderClient.getLastLocationObs(): Maybe<Location>
        = RxFusedLocationProviderClient(this).lastLocation

fun FusedLocationProviderClient.flushLocationsObs(): Completable = RxFusedLocationProviderClient(this).flushLocations()
@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.getLocationAvailabilityObs(): Maybe<LocationAvailability>
        = RxFusedLocationProviderClient(this).locationAvailability

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.setMockLocationObs(mock: Location): Completable
        = RxFusedLocationProviderClient(this).setMockLocation(mock)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.setMockModeObs(isMockMode: Boolean): Completable
        = RxFusedLocationProviderClient(this).setMockMode(isMockMode)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.requestLocationRequestUpdatesObs(request: LocationRequest): Observable<LocationResult>
        = RxFusedLocationProviderClient(this).requestLocationRequestUpdates(request)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.requestLocationAvailabilityUpdatesObs(request: LocationRequest): Observable<LocationAvailability>
        = RxFusedLocationProviderClient(this).requestLocationAvailabilityUpdates(request)

@RequiresPermission(anyOf = arrayOf("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"))
fun FusedLocationProviderClient.requestLocationUpdatesObs(request: LocationRequest, callbackIntent: PendingIntent): Completable
        = RxFusedLocationProviderClient(this).requestLocationUpdates(request, callbackIntent)

fun FusedLocationProviderClient.removeLocationUpdatesObs(callbackIntent: PendingIntent): Completable
        = RxFusedLocationProviderClient(this).removeLocationUpdates(callbackIntent)

fun SettingsClient.checkLocationSettingsObs(request: LocationSettingsRequest): Single<LocationSettingsResponse>
        = RxSettingsClient(this).checkLocationSettings(request)

@RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
fun GeofencingClient.addGeofencesObs(request: GeofencingRequest, intent: PendingIntent): Completable
        = RxGeofencingClient(this).addGeofences(request, intent)

fun GeofencingClient.removeGeofencesObs(geofenceRequestIds: List<String>): Completable
        = RxGeofencingClient(this).removeGeofences(geofenceRequestIds)

fun GeofencingClient.removeGeofencesObs(pendingIntent: PendingIntent): Completable
        = RxGeofencingClient(this).removeGeofences(pendingIntent)

@RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
fun ActivityRecognitionClient.requestActivityUpdatesObs(detectionIntervalMillis: Long, callbackIntent: PendingIntent): Completable
        = RxActivityRecognitionClient(this).requestActivityUpdates(detectionIntervalMillis, callbackIntent)

@RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
fun ActivityRecognitionClient.removeActivityUpdatesObs(callbackIntent: PendingIntent): Completable
        = RxActivityRecognitionClient(this).removeActivityUpdates(callbackIntent)
