package com.nicolasmilliard.rxwallet;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.nicolasmilliard.rxtask.SingleTask;

import io.reactivex.Single;

import static com.nicolasmilliard.rxtask.internal.Preconditions.checkNotNull;

public class RxPaymentsClient {

    private final PaymentsClient client;

    public RxPaymentsClient(@NonNull Context context, @NonNull Wallet.WalletOptions options) {
        checkNotNull(context, "context == null");
        checkNotNull(options, "options == null");
        this.client = Wallet.getPaymentsClient(context, options);
    }

    @NonNull
    public Single<Boolean> isReadyToPay(IsReadyToPayRequest request) {
        return SingleTask.fromPlayTask(() -> client.isReadyToPay(request));
    }

    @NonNull
    public Single<PaymentData> loadPaymentData(PaymentDataRequest request) {
        return SingleTask.fromPlayTask(() -> client.loadPaymentData(request));
    }
}
