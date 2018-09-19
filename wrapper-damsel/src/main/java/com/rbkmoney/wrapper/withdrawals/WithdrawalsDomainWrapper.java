package com.rbkmoney.withdrawals;

import com.rbkmoney.damsel.DomainWrapper;
import com.rbkmoney.damsel.domain.TransactionInfo;

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
