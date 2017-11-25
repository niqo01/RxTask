package com.nicolasmilliard.rxtask

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.plugins.RxJavaPlugins

class TaskCompletable(private val task: Task<Void>) : Completable() {
    override fun subscribeActual(observer: CompletableObserver) {
        val callback = TaskCallback(observer)
        observer.onSubscribe(callback)
        task.addOnCompleteListener(callback)
    }

    internal class TaskCallback(private val observer: CompletableObserver) : Disposable, OnCompleteListener<Void> {
        private var disposed = false

        override fun onComplete(task: Task<Void>) {
            if (disposed) return
            if (task.isSuccessful) {
                observer.onComplete()
            } else {
                try {
                    observer.onError(task.exception!!)
                } catch (inner: Throwable) {
                    Exceptions.throwIfFatal(inner)
                    RxJavaPlugins.onError(CompositeException(task.exception, inner))
                }
            }
        }

        override fun isDisposed(): Boolean = disposed

        override fun dispose() {
            disposed = true
        }

    }
}