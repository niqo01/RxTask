package com.nicolasmilliard.rxauth;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;
import com.nicolasmilliard.rxtask.TaskSupplier;

import io.reactivex.Single;

public class RxGoogleSignIn {

    @NonNull
    public static Single<GoogleSignInAccount> getSignedInAccountFromIntent(@NonNull Intent data) {
        return SingleTask.create(() -> GoogleSignIn.getSignedInAccountFromIntent(data));
    }

    private RxGoogleSignIn() {
    }
}
