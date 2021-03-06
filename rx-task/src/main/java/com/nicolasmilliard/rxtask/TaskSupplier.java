package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

public interface TaskSupplier<T extends Task> {

    /**
     * Supply here the task to run.
     *
     * @return a Task
     */
    T get();
}
