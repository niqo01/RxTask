package com.nicolasmilliard.rxlocation

import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.nicolasmilliard.rxtask.FailureTaskStub
import com.nicolasmilliard.rxtask.SuccessTaskStub
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationResultObservableTest {

    @Mock
    var mockClient: FusedLocationProviderClient? = null

    @Test
    fun testSuccess() {
        var callback: LocationCallback? = null

        `when`(mockClient!!.requestLocationUpdates(any(LocationRequest::class.java), any(LocationCallback::class.java), any(Looper::class.java)))
                .thenAnswer({
                    callback = it.arguments[1] as LocationCallback
                    SuccessTaskStub<Void>(null)
                })

        `when`(mockClient!!.removeLocationUpdates(any(LocationCallback::class.java)))
                .thenReturn(SuccessTaskStub<Void>(null))

        val obs = LocationResultObservable(mockClient!!, LocationRequest.create())

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        callback!!.onLocationResult(LocationResult.create(ArrayList()))

        testObserver.assertSubscribed()
                .assertValue({ res -> res is LocationResult })
                .assertNoErrors()

        callback!!.onLocationResult(LocationResult.create(ArrayList()))

        assertTrue(testObserver.valueCount() == 2)
        testObserver.assertSubscribed()
                .assertValueAt(1, { res -> res is LocationResult })
                .assertNoErrors()

        testObserver.dispose()

        testObserver.assertNoErrors()
                .assertNotComplete()

        callback!!.onLocationResult(LocationResult.create(ArrayList()))

        assertTrue(testObserver.valueCount() == 2)
        testObserver.assertNoErrors()
                .assertNotComplete()
    }

    @Test
    fun testFailure() {
        val failureTask = FailureTaskStub<Void>(Exception("Test"))
        var callback: LocationCallback? = null

        `when`(mockClient!!.requestLocationUpdates(any(LocationRequest::class.java), any(LocationCallback::class.java), any(Looper::class.java)))
                .thenAnswer({
                    callback = it.arguments[1] as LocationCallback
                    failureTask
                })

        `when`(mockClient!!.removeLocationUpdates(any(LocationCallback::class.java)))
                .thenReturn(SuccessTaskStub<Void>(null))

        val obs = LocationResultObservable(mockClient!!, LocationRequest.create())

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        failureTask.complete()

        testObserver.assertFailure(Exception::class.java)

        callback!!.onLocationResult(LocationResult.create(ArrayList()))
    }

    @Test
    fun testDispose() {
        var callback: LocationCallback? = null

        `when`(mockClient!!.requestLocationUpdates(any(LocationRequest::class.java), any(LocationCallback::class.java), any(Looper::class.java)))
                .thenAnswer({
                    callback = it.arguments[1] as LocationCallback
                    SuccessTaskStub<Void>(null)
                })

        `when`(mockClient!!.removeLocationUpdates(any(LocationCallback::class.java)))
                .thenReturn(SuccessTaskStub<Void>(null))

        val obs = LocationResultObservable(mockClient!!, LocationRequest.create())

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        testObserver.dispose()
        callback!!.onLocationResult(LocationResult.create(ArrayList()))

        testObserver.assertSubscribed()
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete()
    }
}