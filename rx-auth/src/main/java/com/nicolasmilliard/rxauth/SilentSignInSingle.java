package com.nicolasmilliard.rxauth;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class SilentSignInSingle extends SingleTask<GoogleSignInAccount> {

    final GoogleSignInClient client;


    public SilentSignInSingle(GoogleSignInClient client) {
        this.client = client;
    }

    @Override
    protected Task<GoogleSignInAccount> run() {
        return client.silentSignIn();
    }
}
