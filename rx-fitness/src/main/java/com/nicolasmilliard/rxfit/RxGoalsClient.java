package com.nicolasmilliard.rxfit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.GoalsClient;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.nicolasmilliard.rxtask.SingleTask;

import java.util.List;

import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

public class RxGoalsClient {

    private final GoalsClient client;

    public RxGoalsClient(@NonNull Context context, @Nullable GoogleSignInAccount account) {
        checkNotNull(context, "context == null");
        this.client = Fitness.getGoalsClient(context, account);
    }

    @NonNull
    public Single<List<Goal>> readCurrentGoals(GoalsReadRequest request) {
        return SingleTask.fromPlayTask(() -> client.readCurrentGoals(request));
    }
}
