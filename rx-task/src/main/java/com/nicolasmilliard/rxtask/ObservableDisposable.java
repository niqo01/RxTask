package com.nicolasmilliard.rxtask;


import com.google.android.gms.tasks.Task;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.disposables.Disposable;

abstract class ObservableDisposable implements Disposable {


    private AtomicBoolean unsubscribed = new AtomicBoolean(false);

    @Override
    public void dispose() {
        if (unsubscribed.getAndSet(true)){
            onDispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return unsubscribed.get();
    }

    protected abstract void onDispose();
}
