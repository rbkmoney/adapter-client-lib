package com.rbkmoney.java.damsel.utils.creators;

import com.rbkmoney.damsel.domain.Failure;
import com.rbkmoney.damsel.domain.TransactionInfo;
import com.rbkmoney.damsel.msgpack.Value;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Intent;
import com.rbkmoney.damsel.withdrawals.provider_adapter.FinishStatus;
import com.rbkmoney.damsel.withdrawals.provider_adapter.ProcessResult;

public class WithdrawalsProviderAdapterPackageCreators {

    // ProxyResult
    public static ProcessResult createProcessResult(Intent intent, Value nextState) {
        return new ProcessResult(intent).setNextState(nextState);
    }

    public static ProcessResult createProcessResult(Intent intent) {
        return WithdrawalsProviderAdapterPackageCreators.createProcessResult(intent, null);
    }

    // FinishIntent
    public static Intent createFinishIntentSuccess(TransactionInfo transactionInfo) {
        return Intent.finish(new com.rbkmoney.damsel.withdrawals.provider_adapter.FinishIntent(createFinishStatusSuccess(transactionInfo)));
    }

    public static FinishStatus createFinishStatusSuccess(TransactionInfo transactionInfo) {
        return FinishStatus.success(new com.rbkmoney.damsel.withdrawals.provider_adapter.Success(transactionInfo));
    }

    public static ProcessResult createProcessResultFailure(Failure failure) {
        return createProcessResult(Intent.finish(new com.rbkmoney.damsel.withdrawals.provider_adapter.FinishIntent(createFinishStatusFailure(failure))));
    }

    public static FinishStatus createFinishStatusFailure(Failure failure) {
        return FinishStatus.failure(failure);
    }

}
