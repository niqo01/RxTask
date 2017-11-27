package com.nicolasmilliard.rxfit

import com.google.android.gms.fitness.SensorsClient
import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.request.OnDataPointListener
import com.google.android.gms.fitness.request.SensorRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.plugins.RxJavaPlugins

class SensorObservable(private val client: SensorsClient, private val request: SensorRequest) : Observable<DataPoint>() {
    override fun subscribeActual(observer: Observer<in DataPoint>) {
        val callback = SensorCallback(client, observer)
        observer.onSubscribe(callback)
        client.add(request, callback).addOnCompleteListener(callback)
    }

    internal class SensorCallback(private val client: SensorsClient, private val observer: Observer<in DataPoint>) : Disposable, OnCompleteListener<Void>, OnDataPointListener {
        override fun onDataPoint(dataPoint: DataPoint) {
            if (disposed) return
            observer.onNext(dataPoint)
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
            client.remove(this).addOnCompleteListener({
                // Not sure what to do here
            })
        }

    }
}