package com.nicolasmilliard.rxtask

import com.google.android.gms.tasks.Task
import org.junit.Test


class TaskMaybeTest {

    class MaybeTest(val task: Task<Any>) : MaybeTask<Any>() {
        override fun run(): Task<Any> {
            return task
        }
    }

    @Test
    fun testMaybeSuccess() {
        val value = ""
        var taskStub = SuccessTaskStub<Any>(value)
        val taskMaybe = MaybeTest(taskStub)

        val testObserver = taskMaybe.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertResult(value)
    }

    @Test
    fun testMaybeComplete() {
        val value: Any? = null
        var taskStub = SuccessTaskStub(value)
        val taskMaybe = MaybeTest(taskStub)

        val testObserver = taskMaybe.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertNoValues()
                .assertComplete()
    }

    @Test
    fun testMaybeFailure() {
        var taskStub = FailureTaskStub<Any>(Exception("Test"))
        val taskMaybe = MaybeTest(taskStub)

        val testObserver = taskMaybe.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertFailure(Exception::class.java)
    }

    @Test
    fun testMaybeDispose() {
        var taskStub = SuccessTaskStub<Any>("")

        val taskMaybe = MaybeTest(taskStub)

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