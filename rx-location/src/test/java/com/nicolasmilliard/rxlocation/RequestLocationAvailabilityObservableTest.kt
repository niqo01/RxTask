package com.nicolasmilliard.rxlocation

import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.nicolasmilliard.rxtask.FailureTaskStub
import com.nicolasmilliard.rxtask.SuccessTaskStub
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RequestLocationAvailabilityObservableTest {

    @Mock
    var mockClient: FusedLocationProviderClient? = null

    @Test
    fun testFailure() {
        val failureTask = FailureTaskStub<Void>(Exception("Test"))

        `when`(mockClient!!.requestLocationUpdates(any(LocationRequest::class.java), any(LocationCallback::class.java), any(Looper::class.java)))
                .thenAnswer({
                    failureTask
                })

        `when`(mockClient!!.removeLocationUpdates(any(LocationCallback::class.java)))
                .thenReturn(SuccessTaskStub<Void>(null))

        val obs = RequestLocationAvailabilityObservable(mockClient!!, LocationRequest.create())

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        failureTask.complete()

        testObserver.assertFailure(Exception::class.java)

    }

    @Test
    fun testDispose() {

        `when`(mockClient!!.requestLocationUpdates(any(LocationRequest::class.java), any(LocationCallback::class.java), any(Looper::class.java)))
                .thenAnswer({
                    SuccessTaskStub<Void>(null)
                })

        `when`(mockClient!!.removeLocationUpdates(any(LocationCallback::class.java)))
                .thenReturn(SuccessTaskStub<Void>(null))

        val obs = RequestLocationAvailabilityObservable(mockClient!!, LocationRequest.create())

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        testObserver.dispose()

        testObserver.assertSubscribed()
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete()
    }
}