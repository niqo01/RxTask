package com.nicolasmilliard.rxtask

import org.junit.Test


class TaskMaybeTest {

    @Test
    fun testMaybeSuccess() {
        val value = ""
        var taskStub = SuccessTaskStub<Any>(value)
        val taskMaybe = RxTask.maybe(taskStub)

        val testObserver = taskMaybe.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertResult(value)
    }

    @Test
    fun testMaybeComplete() {
        val value: Any? = null
        var taskStub = SuccessTaskStub(value)
        val taskMaybe = RxTask.maybe(taskStub)

        val testObserver = taskMaybe.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertNoValues()
                .assertComplete()
    }

    @Test
    fun testMaybeFailure() {
        var taskStub = FailureTaskStub<Any>(Exception("Test"))
        val taskMaybe = TaskMaybe(taskStub)

        val testObserver = taskMaybe.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertFailure(Exception::class.java)
    }

    @Test
    fun testMaybeDispose() {
        var taskStub = SuccessTaskStub<Any>("")

        val taskMaybe = RxTask.maybe(taskStub)

        val testObserver = taskMaybe.test()

        testObserver.assertSubscribed()

        testObserver.dispose()
        taskStub.complete()
        testObserver.assertSubscribed()
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete()
    }
}