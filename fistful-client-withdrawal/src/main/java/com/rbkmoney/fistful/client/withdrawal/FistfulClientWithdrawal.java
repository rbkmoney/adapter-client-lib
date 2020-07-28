package com.rbkmoney.fistful.client.withdrawal;

import com.rbkmoney.damsel.withdrawals.provider_adapter.AdapterHostSrv;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Callback;
import com.rbkmoney.damsel.withdrawals.provider_adapter.ProcessCallbackResult;
import com.rbkmoney.fistful.client.withdrawal.exception.FistfulClientWithdrawalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FistfulClientWithdrawal {

    private final AdapterHostSrv.Iface adapterHostSrv;

    public ProcessCallbackResult processCallback (Callback callback) {
        log.info("ProcessCallback with callback={}", callback);
        try {
            return adapterHostSrv.processCallback(callback);
        } catch (TException ex) {
            throw new FistfulClientWithdrawalException(String.format("Can't extract response from processCallback, callback=%s", callback), ex);
        }
    }

}