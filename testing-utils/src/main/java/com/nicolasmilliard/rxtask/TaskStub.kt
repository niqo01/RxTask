package com.nicolasmilliard.rxtask

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import java.lang.Exception
import java.util.concurrent.Executor

abstract class TaskStub<T> : Task<T>() {
    private var complete: Boolean = false
    private var listener: OnCompleteListener<T>? = null

    fun complete() {
        complete = true
        listener!!.onComplete(this)
    }

    override fun isComplete(): Boolean {
        return complete
    }

    override fun addOnCompleteListener(listener: OnCompleteListener<T>): Task<T> {
        this.listener = listener
        return this
    }

    override fun addOnFailureListener(p0: OnFailureListener): Task<T> {
        return this
    }

    override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<T> {
        return this
    }

    override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<T> {
        return this
    }


    override fun addOnSuccessListener(p0: Executor, p1: OnSuccessListener<in T>): Task<T> {
        return this
    }

    override fun addOnSuccessListener(p0: OnSuccessListener<in T>): Task<T> {
        return this
    }

    override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in T>): Task<T> {
        return this
    }

}

class SuccessTaskStub<T>(private val value: T?) : TaskStub<T>() {
    override fun isSuccessful(): Boolean {
        return true
    }

    override fun <X : Throwable?> getResult(p0: Class<X>): T? {
        return value
    }

    override fun getResult(): T? {
        return if (isComplete) value else null
    }

    override fun getException(): Exception? {
        return null
    }

}

class FailureTaskStub<T>(private val value: Exception) : TaskStub<T>() {
    override fun isSuccessful(): Boolean {
        return false
    }

    override fun <X : Throwable?> getResult(p0: Class<X>): T? {
        return null
    }

    override fun getResult(): T? {
        return null
    }

    override fun getException(): Exception? {
        return if (isComplete) value else null
    }
}