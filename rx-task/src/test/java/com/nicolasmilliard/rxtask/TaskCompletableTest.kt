package com.nicolasmilliard.rxtask

import com.google.android.gms.tasks.Task
import org.junit.Test


class TaskCompletableTest {

    class CompletableTest(val task: Task<Void>) : CompletableTask() {
        override fun run(): Task<Void> {
            return task
        }
    }

    @Test
    fun testCompletableSuccess() {
        var taskStub = SuccessTaskStub<Void>(null)
        val taskCompletable = CompletableTest(taskStub)

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
        val taskCompletable = CompletableTest(taskStub)

        val testObserver = taskCompletable.test()

        testObserver.assertSubscribed()

        taskStub.complete()

        testObserver.assertFailure(Exception::class.java)
    }

    @Test
    fun testCompletableDispose() {
        var taskStub = SuccessTaskStub<Void>(null)

        val taskCompletable = CompletableTest(taskStub)

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