package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

public interface TaskSupplier<T extends Task> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
