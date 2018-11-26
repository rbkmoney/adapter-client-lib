package com.rbkmoney.java.damsel.utils.domain;

public enum TargetInvoicePaymentStatus {
    PROCESSED,
    CAPTURED,
    CANCELLED,
    REFUNDED;

    TargetInvoicePaymentStatus valueFromThrift(com.rbkmoney.damsel.domain.TargetInvoicePaymentStatus targetInvoicePaymentStatus) {
        if (targetInvoicePaymentStatus.isSetProcessed()) {
            return PROCESSED;
        } else if (targetInvoicePaymentStatus.isSetCaptured()) {
            return CAPTURED;
        } else if (targetInvoicePaymentStatus.isSetCancelled()) {
            return CANCELLED;
        } else if (targetInvoicePaymentStatus.isSetRefunded()) {
            return REFUNDED;
        }
        return null;
    }
}
