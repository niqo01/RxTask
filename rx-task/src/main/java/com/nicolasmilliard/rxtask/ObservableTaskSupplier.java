package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

public interface ObservableTaskSupplier<T> {

    /**
     * Supply here the task to run.
     *
     * @return a Task
     */
    Task<Void> get(ObservableTaskCallback<T> callback);
}
