package com.nicolasmilliard.rxtask

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ObservableTaskTest {


    @Test
    fun testSuccess() {

        var taskStub = SuccessTaskStub<Void>(null)

        var obsCallback: ObservableTaskCallback<Any>? = null
        var wasDisposed = false
        val disposeCallback: ObservableTaskCallback.Dispose = ObservableTaskCallback.Dispose {
            wasDisposed = true
        }

        val obs = ObservableTask.fromPlayTask<Any>(ObservableTaskSupplier { callback ->
            obsCallback = callback
            callback.setDisposeListener(disposeCallback)
            taskStub
        })

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        obsCallback!!.onNext("")

        testObserver.assertSubscribed()
                .assertValue("")
                .assertNoErrors()

        obsCallback!!.onNext("1")

        assertTrue(testObserver.valueCount() == 2)
        testObserver.assertSubscribed()
                .assertValues("", "1")
                .assertNoErrors()

        obsCallback!!.onComplete()

        assertFalse("ObservableTask did not dispose properly", wasDisposed)

        testObserver.assertNoErrors()
                .assertComplete()
    }

    @Test
    fun testFailure() {
        val failureTask = FailureTaskStub<Void>(Exception("Test"))

        var obsCallback: ObservableTaskCallback<Any>? = null
        var wasDisposed = false
        val disposeCallback: ObservableTaskCallback.Dispose = ObservableTaskCallback.Dispose {
            wasDisposed = true
        }

        val obs = ObservableTask.fromPlayTask<Any>(ObservableTaskSupplier { callback ->
            obsCallback = callback
            callback.setDisposeListener(disposeCallback)
            failureTask
        })

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        failureTask.complete()

        testObserver.assertFailure(Exception::class.java)

    }

    @Test
    fun testDispose() {
        var taskStub = SuccessTaskStub<Void>(null)

        var obsCallback: ObservableTaskCallback<Any>? = null
        var wasDisposed = false
        val disposeCallback: ObservableTaskCallback.Dispose = ObservableTaskCallback.Dispose {
            wasDisposed = true
        }

        val obs = ObservableTask.fromPlayTask<Any>(ObservableTaskSupplier { callback ->
            obsCallback = callback
            callback.setDisposeListener(disposeCallback)
            taskStub
        })

        val testObserver = obs.test()

        testObserver.assertSubscribed()

        testObserver.dispose()

        assertTrue("ObservableTask did not dispose properly", wasDisposed)
        obsCallback!!.onNext("1")

        testObserver.assertSubscribed()
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete()
    }
}