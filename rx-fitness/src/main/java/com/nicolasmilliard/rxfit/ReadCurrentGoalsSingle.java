package com.nicolasmilliard.rxfit;

import com.google.android.gms.fitness.GoalsClient;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.tasks.Task;
import com.nicolasmilliard.rxtask.SingleTask;

import java.util.List;


final class ReadCurrentGoalsSingle extends SingleTask<List<Goal>> {

    final GoalsClient client;
    final GoalsReadRequest request;

    public ReadCurrentGoalsSingle(GoalsClient client, GoalsReadRequest request) {
        this.client = client;
        this.request = request;
    }

    @Override
    protected Task<List<Goal>> run() {
        return client.readCurrentGoals(request);
    }
}
