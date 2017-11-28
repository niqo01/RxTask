package com.nicolasmilliard.rxtask

import org.junit.Test


class TaskCompletableTest {

    @Test
    fun testCompletableSuccess() {
        var taskStub = SuccessTaskStub<Void>(null)
        val taskCompletable = RxTask.toCompletable(taskStub)

        val testObserver = taskCompletable.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertSubscribed()
                .assertNoErrors()
                .assertComplete();
    }

    @Test
    fun testCompletableFailure() {
        var taskStub = FailureTaskStub<Void>(Exception("Test"))
        val taskCompletable = RxTask.toCompletable(taskStub)

        val testObserver = taskCompletable.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertFailure(Exception::class.java)
    }

    @Test
    fun testCompletableDispose() {
        var taskStub = SuccessTaskStub<Void>(null)

        val taskCompletable = RxTask.toCompletable(taskStub)

        val testObserver = taskCompletable.test()

        testObserver.assertSubscribed()

        testObserver.dispose()
        taskStub.complete()
        testObserver.assertSubscribed()
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete()
    }
}