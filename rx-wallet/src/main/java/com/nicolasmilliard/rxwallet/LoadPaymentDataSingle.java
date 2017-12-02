package com.nicolasmilliard.rxwallet;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.nicolasmilliard.rxtask.SingleTask;


final class LoadPaymentDataSingle extends SingleTask<PaymentData> {

    final PaymentsClient client;
    final PaymentDataRequest request;


    public LoadPaymentDataSingle(PaymentsClient client, PaymentDataRequest request) {
        this.client = client;
        this.request = request;
    }

    @Override
    protected Task<PaymentData> run() {
        return client.loadPaymentData(request);
    }
}
