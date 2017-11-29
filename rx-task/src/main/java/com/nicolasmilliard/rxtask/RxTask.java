package com.nicolasmilliard.rxtask;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables}.
 */
public final class RxTask {

    private RxTask() {
        throw new AssertionError("No instances.");
    }

    /**
     * Create an Single which emits on {@code task} events.
     */
    @NonNull
    public static <T> Single<T> toSingle(@NonNull Task<T> task) {
        checkNotNull(task, "task == null");
        return new TaskSingle<T>(task);
    }

    /**
     * Create an Single which emits on {@code task} events.
     */
    @NonNull
    public static <T> Maybe<T> toMaybe(@NonNull Task<T> task) {
        checkNotNull(task, "task == null");
        return new TaskMaybe<T>(task);
    }

    /**
     * Create an Completable which complete on {@code task} complete event.
     */
    @NonNull
    public static <T> Completable toCompletable(@NonNull Task<T> task) {
        checkNotNull(task, "task == null");
        return new TaskCompletable(task);
    }
}
