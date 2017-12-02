package com.nicolasmilliard.rxauth;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import io.reactivex.Single;

public class RxGoogleSignIn {

    @NonNull
    public static Single<GoogleSignInAccount> getSignedInAccountFromIntent(@NonNull Intent data) {
        return new GetSignedInAccountFromIntentSingle(data);
    }

    private RxGoogleSignIn() {
    }
}
