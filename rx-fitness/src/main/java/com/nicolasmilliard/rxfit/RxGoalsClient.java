package com.nicolasmilliard.rxfit;

import android.support.annotation.NonNull;

import com.google.android.gms.fitness.GoalsClient;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.fitness.request.GoalsReadRequest;

import java.util.List;

import io.reactivex.Single;

public class RxGoalsClient {

    private final GoalsClient client;

    public RxGoalsClient(GoalsClient client) {
        this.client = client;
    }

    @NonNull
    public Single<List<Goal>> readCurrentGoals(GoalsReadRequest request) {
        return new ReadCurrentGoalsSingle(client, request);
    }
}
