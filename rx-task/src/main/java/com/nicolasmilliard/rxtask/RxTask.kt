package com.nicolasmilliard.rxtask

import com.google.android.gms.tasks.Task
import io.reactivex.*

fun <T> Task<T>.toSingle(): Single<T> = TaskSingle<T>(this)
fun <T> Task<T>.toMaybe(): Maybe<T> = TaskMaybe<T>(this)
fun Task<Void>.toCompletable(): Completable = TaskCompletable(this)


