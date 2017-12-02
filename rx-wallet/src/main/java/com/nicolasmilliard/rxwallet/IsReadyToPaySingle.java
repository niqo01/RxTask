package com.nicolasmilliard.rxwallet;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.nicolasmilliard.rxtask.SingleTask;


final class IsReadyToPaySingle extends SingleTask<Boolean> {

    final PaymentsClient client;
    final IsReadyToPayRequest request;


    public IsReadyToPaySingle(PaymentsClient client, IsReadyToPayRequest request) {
        this.client = client;
        this.request = request;
    }

    @Override
    protected Task<Boolean> run() {
        return client.isReadyToPay(request);
    }
}
