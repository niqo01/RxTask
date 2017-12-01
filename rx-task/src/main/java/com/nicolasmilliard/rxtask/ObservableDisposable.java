package com.nicolasmilliard.rxtask;


import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.disposables.Disposable;

abstract class ObservableDisposable implements Disposable {


    private AtomicBoolean unsubscribed = new AtomicBoolean(false);

    @Override
    public void dispose() {
        if (unsubscribed.getAndSet(true)) {
            onDispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return unsubscribed.get();
    }

    protected abstract void onDispose();
}
