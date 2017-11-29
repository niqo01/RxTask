package com.nicolasmilliard.rxtask

import org.junit.Test


class TaskSingleTest {

    @Test
    fun testSingleSuccess() {
        val value = ""
        var taskStub = SuccessTaskStub<Any>(value)
        val taskSingle = RxTask.single(taskStub)

        val testObserver = taskSingle.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertResult(value)
    }

    @Test
    fun testSingleFailure() {
        var taskStub = FailureTaskStub<Any>(Exception("Test"))
        val taskSingle = RxTask.single(taskStub)

        val testObserver = taskSingle.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertFailure(Exception::class.java)
    }

    @Test
    fun testSingleDispose() {
        var taskStub = SuccessTaskStub<Any>("")

        val taskSingle = RxTask.single(taskStub)

        val testObserver = taskSingle.test()

        testObserver.assertSubscribed()

        testObserver.dispose()
        taskStub.complete()
        testObserver.assertSubscribed()
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete()
    }
}