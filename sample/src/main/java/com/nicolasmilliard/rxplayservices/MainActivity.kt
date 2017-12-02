package com.nicolasmilliard.rxplayservices

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.nicolasmilliard.rxlocation.RxFusedLocationProviderClient
import com.nicolasmilliard.rxlocation.RxSettingsClient
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private val REQUEST_CHECK_SETTINGS: Int = 1001
    private val PERMISSIONS_REQUEST_FINE_LOCATION: Int = 1002

    lateinit var fusedLocationProviderClient: RxFusedLocationProviderClient
    lateinit var settingsClient: RxSettingsClient

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        fusedLocationProviderClient = RxFusedLocationProviderClient(application)
        settingsClient = RxSettingsClient(application)

        checkPermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    @SuppressLint("MissingPermission")
    private fun checkSettings() {
        val settingsRequest = LocationSettingsRequest.Builder()
                .addLocationRequest(getLocationRequest()).build()
        disposables.add(settingsClient.checkLocationSettings(settingsRequest)
                .flatMapObservable { fusedLocationProviderClient.requestLocationRequestUpdates(getLocationRequest()) }
                .take(2)
                .subscribe(this::updateViews, this::handleException))
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(this,
                    Array(1) { Manifest.permission.ACCESS_FINE_LOCATION },
                    PERMISSIONS_REQUEST_FINE_LOCATION);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        } else {
            checkSettings()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            checkSettings()
        }
    }

    private fun updateViews(result: LocationResult) {
        textView.text = "lat:${result.lastLocation.latitude}, lng: ${result.lastLocation.longitude}"
    }

    private fun handleException(e: Throwable) {
        if (e is ApiException) {
            when (e.statusCode) {
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {// Location settings are not satisfied. But could be fixed by showing the
                    // user a dialog.
                    try {
                        if (e is ResolvableApiException) {
                            e.startResolutionForResult(
                                    this,
                                    REQUEST_CHECK_SETTINGS);
                        }
                    } catch (e1: IntentSender.SendIntentException) {
                        Snackbar.make(content_main, "Sending Intent Exception", Snackbar.LENGTH_LONG).show()
                    }
                }
                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    // Location settings are not satisfied. However, we have no way to fix the
                    // settings so we won't show the dialog.
                    Snackbar.make(content_main, "Location settings not satisfied", Snackbar.LENGTH_LONG).show()
                }
                else -> {
                    Snackbar.make(content_main, "Unknown error", Snackbar.LENGTH_LONG).show()
                }

            }
        } else {
            Snackbar.make(content_main, "Unknown error", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun getLocationRequest(): LocationRequest {
        return LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setSmallestDisplacement(10f)
    }

}
