package com.rbkmoney.java.damsel.utils.extractors;

import com.rbkmoney.damsel.domain.ContactInfo;
import com.rbkmoney.damsel.domain.DisposablePaymentResource;
import com.rbkmoney.damsel.domain.TargetInvoicePaymentStatus;
import com.rbkmoney.damsel.proxy_provider.*;

import java.util.Map;

import static com.rbkmoney.java.damsel.constant.Error.UNKNOWN;

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
        return extractInvoiceId(context.getPaymentInfo());
    }

    public static String extractInvoiceId(PaymentInfo paymentInfo) {
        return paymentInfo.getInvoice().getId();
    }

    public static String extractPaymentId(PaymentContext context) {
        return extractPaymentId(context.getPaymentInfo());
    }

    public static String extractPaymentId(PaymentInfo paymentInfo) {
        return paymentInfo.getPayment().getId();
    }

    public static String extractRecurrentToken(PaymentContext context) {
        return context.getPaymentInfo().getPayment().getPaymentResource().getRecurrentPaymentResource().getRecToken();
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

    public static ContactInfo extractPaymentInfo(PaymentContext context) {
        return context.getPaymentInfo().getPayment().getContactInfo();
    }

    public static Cash extractCashPayment(PaymentContext context) {
        return context.getPaymentInfo().getPayment().getCost();
    }

    public static Cash extractCashRefund(PaymentContext context) {
        return context.getPaymentInfo().getRefund().getCash();
    }

    public static Cash extractCashRecurrentToken(RecurrentTokenContext context) {
        return context.getTokenInfo().getPaymentTool().getMinimalPaymentCost();
    }

    public static Map<String, String> extractTrxExtra(PaymentContext context) {
        return context.getPaymentInfo().getPayment().getTrx().getExtra();
    }

    public static String extractTargetInvoicePaymentStatus(PaymentContext paymentContext) {
        return extractTargetInvoicePaymentStatus(paymentContext.getSession().getTarget());
    }

    public static String extractTargetInvoicePaymentStatus(TargetInvoicePaymentStatus targetInvoicePaymentStatus) {
        String state = UNKNOWN;
        if (targetInvoicePaymentStatus.isSetProcessed()) {
            state = TargetInvoicePaymentStatus._Fields.PROCESSED.getFieldName();
        } else if (targetInvoicePaymentStatus.isSetCaptured()) {
            state = TargetInvoicePaymentStatus._Fields.CAPTURED.getFieldName();
        } else if (targetInvoicePaymentStatus.isSetCancelled()) {
            state = TargetInvoicePaymentStatus._Fields.CANCELLED.getFieldName();
        } else if (targetInvoicePaymentStatus.isSetRefunded()) {
            state = TargetInvoicePaymentStatus._Fields.REFUNDED.getFieldName();
        }
        return state.toUpperCase();
    }

}
