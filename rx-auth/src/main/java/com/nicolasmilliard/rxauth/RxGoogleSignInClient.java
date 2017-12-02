package com.nicolasmilliard.rxauth;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

public class RxGoogleSignInClient {

    private final GoogleSignInClient client;

    public RxGoogleSignInClient(@NonNull Context context, @NonNull GoogleSignInOptions options) {
        checkNotNull(context, "context == null");
        checkNotNull(options, "options == null");
        this.client = GoogleSignIn.getClient(context, options);
    }

    @NonNull
    public Intent getSignInIntent() {
        return client.getSignInIntent();
    }

    @NonNull
    public Completable revokeAccess() {
        return new RevokeAccessCompletable(client);
    }

    @NonNull
    public Completable signOut() {
        return new SignOutCompletable(client);
    }

    @NonNull
    public Single<GoogleSignInAccount> silentSignIn() {
        return new SilentSignInSingle(client);
    }
}
