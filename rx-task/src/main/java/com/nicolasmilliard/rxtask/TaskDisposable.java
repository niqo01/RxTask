package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.disposables.Disposable;

abstract class TaskDisposable extends AtomicReference<Task<?>> implements Disposable {


    public TaskDisposable(Task<?> task) {
        super(task);
    }

    @Override
    public void dispose() {
        set(null);
    }

    @Override
    public boolean isDisposed() {
        Task<?> task = get();
        return task == null;
    }

}
