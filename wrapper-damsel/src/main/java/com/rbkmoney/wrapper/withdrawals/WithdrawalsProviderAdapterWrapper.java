package com.rbkmoney.wrapper.withdrawals;

import com.rbkmoney.damsel.domain.Failure;
import com.rbkmoney.damsel.domain.TransactionInfo;
import com.rbkmoney.damsel.msgpack.Value;
import com.rbkmoney.damsel.withdrawals.provider_adapter.FinishStatus;
import com.rbkmoney.damsel.withdrawals.provider_adapter.ProcessResult;

public class WithdrawalsProviderAdapterWrapper {

    // ProxyResult
    public static ProcessResult makeProcessResult(com.rbkmoney.damsel.withdrawals.provider_adapter.Intent intent, Value nextState) {
        return new ProcessResult(intent).setNextState(nextState);
    }

    public static ProcessResult makeProcessResult(com.rbkmoney.damsel.withdrawals.provider_adapter.Intent intent) {
        return WithdrawalsProviderAdapterWrapper.makeProcessResult(intent, null);
    }

    // FinishIntent
    public static com.rbkmoney.damsel.withdrawals.provider_adapter.Intent makeFinishIntentSuccess(TransactionInfo transactionInfo) {
        return com.rbkmoney.damsel.withdrawals.provider_adapter.Intent.finish(new com.rbkmoney.damsel.withdrawals.provider_adapter.FinishIntent(makeFinishStatusSuccess(transactionInfo)));
    }

    public static FinishStatus makeFinishStatusSuccess(TransactionInfo transactionInfo) {
        return FinishStatus.success(new com.rbkmoney.damsel.withdrawals.provider_adapter.Success(transactionInfo));
    }

    public static ProcessResult makeProcessResultFailure(Failure failure) {
        return makeProcessResult(com.rbkmoney.damsel.withdrawals.provider_adapter.Intent.finish(new com.rbkmoney.damsel.withdrawals.provider_adapter.FinishIntent(makeFinishStatusFailure(failure))));
    }

    public static FinishStatus makeFinishStatusFailure(Failure failure) {
        return FinishStatus.failure(failure);
    }

}
