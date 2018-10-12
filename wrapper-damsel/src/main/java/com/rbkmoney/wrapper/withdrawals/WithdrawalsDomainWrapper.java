package com.rbkmoney.wrapper.withdrawals;

import com.rbkmoney.damsel.domain.TransactionInfo;
import com.rbkmoney.wrapper.damsel.DomainWrapper;

import java.util.Map;

public class WithdrawalsDomainWrapper {

    // TransactionInfo
    public static TransactionInfo makeTransactionInfo(String paymentId, Map<String, String> extra, String timestamp) {
        return DomainWrapper.makeTransactionInfo(paymentId, extra, timestamp);
    }

    public static TransactionInfo makeTransactionInfo(String paymentId, Map<String, String> extra) {
        return DomainWrapper.makeTransactionInfo(paymentId, extra, null);
    }

}
