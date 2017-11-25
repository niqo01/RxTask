package com.nicolasmilliard.rxlocation

import android.annotation.SuppressLint
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.plugins.RxJavaPlugins

class LocationAvailabilityObservable(private val client: FusedLocationProviderClient, private val request: LocationRequest) : Observable<LocationAvailability>() {

    @SuppressLint("MissingPermission")
    override fun subscribeActual(observer: Observer<in LocationAvailability>) {
        val callback = SensorCallback(client, observer)
        client.requestLocationUpdates(request, callback, null)
    }

    internal class SensorCallback(private val client: FusedLocationProviderClient, private val observer: Observer<in LocationAvailability>) : LocationCallback(), Disposable, OnCompleteListener<Void> {
        override fun onLocationAvailability(result: LocationAvailability) {
            super.onLocationAvailability(result)
            if (disposed) return
            observer.onNext(result)
        }

        private var disposed = false

        override fun onComplete(task: Task<Void>) {
            if (disposed) return
            if (!task.isSuccessful) {
                try {
                    observer.onError(task.exception!!)
                } catch (inner: Throwable) {
                    Exceptions.throwIfFatal(inner)
                    RxJavaPlugins.onError(CompositeException(task.exception, inner))
                }
            }
        }

        override fun isDisposed(): Boolean = disposed

        override fun dispose() {
            disposed = true
            client.removeLocationUpdates(this).addOnCompleteListener({
                // Not sure what to do here
            })
        }

    }
}