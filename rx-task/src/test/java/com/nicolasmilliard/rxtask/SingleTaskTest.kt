package com.nicolasmilliard.rxtask

import com.google.android.gms.tasks.Task
import org.junit.Test


class SingleTaskTest {

    class SingleTest(val task: Task<Any>) : SingleTask<Any>() {
        override fun run(): Task<Any> {
            return task
        }
    }

    @Test
    fun testSingleSuccess() {
        val value = ""
        var taskStub = SuccessTaskStub<Any>(value)

        val taskSingle = SingleTest(taskStub)

        val testObserver = taskSingle.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertResult(value)
    }

    @Test
    fun testSingleFailure() {
        var taskStub = FailureTaskStub<Any>(Exception("Test"))
        val taskSingle = SingleTest(taskStub)

        val testObserver = taskSingle.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertFailure(Exception::class.java)
    }

    @Test
    fun testSingleDispose() {
        var taskStub = SuccessTaskStub<Any>("")

        val taskSingle = SingleTest(taskStub)

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