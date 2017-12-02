package com.nicolasmilliard.rxauth;

import android.annotation.SuppressLint;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.CompletableTask;


final class RevokeAccessCompletable extends CompletableTask {

    final GoogleSignInClient client;

    public RevokeAccessCompletable(GoogleSignInClient client) {
        this.client = client;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected Task<Void> run() {
        return client.revokeAccess();
    }
}
