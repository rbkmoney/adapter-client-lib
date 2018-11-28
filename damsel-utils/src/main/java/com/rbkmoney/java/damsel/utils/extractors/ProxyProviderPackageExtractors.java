package com.rbkmoney.java.damsel.utils.extractors;

import com.rbkmoney.damsel.domain.DisposablePaymentResource;
import com.rbkmoney.damsel.proxy_provider.PaymentContext;
import com.rbkmoney.damsel.proxy_provider.PaymentResource;
import com.rbkmoney.damsel.proxy_provider.RecurrentPaymentResource;
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenContext;

public class ProxyProviderPackageExtractors {

    public static DisposablePaymentResource extractDisposablePaymentResource(RecurrentTokenContext context) {
        return context.getTokenInfo().getPaymentTool().getPaymentResource();
    }

    public static DisposablePaymentResource extractDisposablePaymentResource(PaymentContext context) {
        return context.getPaymentInfo().getPayment().getPaymentResource().getDisposablePaymentResource();
    }

    public static PaymentResource extractPaymentResource(PaymentContext context) {
        return context.getPaymentInfo().getPayment().getPaymentResource();
    }

    public static String extractRecurrentId(RecurrentTokenContext context) {
        return context.getTokenInfo().getPaymentTool().getId();
    }

    public static String extractInvoiceId(PaymentContext context) {
        return context.getPaymentInfo().getInvoice().getId();
    }

    public static String extractBankCardToken(PaymentResource paymentResource) {
        if (paymentResource.isSetDisposablePaymentResource()) {
            return extractBankCardToken(paymentResource.getDisposablePaymentResource());
        }
        return extractBankCardToken(paymentResource.getRecurrentPaymentResource());
    }

    public static String extractBankCardToken(RecurrentPaymentResource paymentResource) {
        return paymentResource.getPaymentTool().getBankCard().getToken();
    }

    public static String extractBankCardToken(DisposablePaymentResource paymentResource) {
        return paymentResource.getPaymentTool().getBankCard().getToken();
    }
}
