package com.nicolasmilliard.rxfit

import com.google.android.gms.fitness.SensorsClient
import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.data.DataSource
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.OnDataPointListener
import com.google.android.gms.fitness.request.SensorRequest
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
class SensorObservableTest {

    @Mock
    var mockClient: SensorsClient? = null
    val dataPoint = DataPoint.create(DataSource.Builder()
            .setDataType(DataType.AGGREGATE_ACTIVITY_SUMMARY)
            .setType(3)
            .build())

    @Test
    fun testSuccess() {
        var callback: OnDataPointListener? = null


        `when`(mockClient!!.add(any(SensorRequest::class.java), any(OnDataPointListener::class.java)))
                .thenAnswer({
                    callback = it.arguments[1] as OnDataPointListener
                    SuccessTaskStub<Void>(null)
                })

        `when`(mockClient!!.remove(any(OnDataPointListener::class.java)))
                .thenReturn(SuccessTaskStub<Boolean>(true))

        val obs = SensorObservable(mockClient!!, SensorRequest.Builder()
                .setDataType(DataType.AGGREGATE_ACTIVITY_SUMMARY).build())

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        callback!!.onDataPoint(dataPoint)

        testObserver.assertSubscribed()
                .assertValue({ res -> res is DataPoint })
                .assertNoErrors()

        callback!!.onDataPoint(dataPoint)

        assertTrue(testObserver.valueCount() == 2)
        testObserver.assertSubscribed()
                .assertValueAt(1, { res -> res is DataPoint })
                .assertNoErrors()

        testObserver.dispose()

        testObserver.assertNoErrors()
                .assertNotComplete()

        callback!!.onDataPoint(dataPoint)

        assertTrue(testObserver.valueCount() == 2)
        testObserver.assertNoErrors()
                .assertNotComplete()
    }

    @Test
    fun testFailure() {
        val failureTask = FailureTaskStub<Void>(Exception("Test"))
        var callback: OnDataPointListener? = null

        `when`(mockClient!!.add(any(SensorRequest::class.java), any(OnDataPointListener::class.java)))
                .thenAnswer({
                    callback = it.arguments[1] as OnDataPointListener
                    failureTask
                })

        `when`(mockClient!!.remove(any(OnDataPointListener::class.java)))
                .thenReturn(SuccessTaskStub<Boolean>(null))

        val obs = SensorObservable(mockClient!!, SensorRequest.Builder()
                .setDataType(DataType.AGGREGATE_ACTIVITY_SUMMARY).build())

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        failureTask.complete()

        testObserver.assertFailure(Exception::class.java)

        callback!!.onDataPoint(dataPoint)
    }

    @Test
    fun testDispose() {
        var callback: OnDataPointListener? = null

        `when`(mockClient!!.add(any(SensorRequest::class.java), any(OnDataPointListener::class.java)))
                .thenAnswer({
                    callback = it.arguments[1] as OnDataPointListener
                    SuccessTaskStub<Void>(null)
                })

        `when`(mockClient!!.remove(any(OnDataPointListener::class.java)))
                .thenReturn(SuccessTaskStub<Boolean>(null))

        val obs = SensorObservable(mockClient!!, SensorRequest.Builder()
                .setDataType(DataType.AGGREGATE_ACTIVITY_SUMMARY).build())

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        testObserver.dispose()
        callback!!.onDataPoint(dataPoint)

        testObserver.assertSubscribed()
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete()
    }
}