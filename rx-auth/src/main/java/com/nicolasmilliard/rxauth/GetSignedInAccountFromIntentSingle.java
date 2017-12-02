package com.nicolasmilliard.rxauth;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;


final class GetSignedInAccountFromIntentSingle extends SingleTask<GoogleSignInAccount> {

    final Intent data;

    public GetSignedInAccountFromIntentSingle(Intent data) {
        this.data = data;
    }

    @Override
    protected Task<GoogleSignInAccount> run() {
        return GoogleSignIn.getSignedInAccountFromIntent(data);
    }
}
