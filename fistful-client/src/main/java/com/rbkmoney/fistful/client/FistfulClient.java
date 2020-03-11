package com.rbkmoney.fistful.client;

import com.rbkmoney.damsel.p2p_adapter.Callback;
import com.rbkmoney.damsel.p2p_adapter.P2PAdapterHostSrv;
import com.rbkmoney.damsel.p2p_adapter.ProcessCallbackResult;
import com.rbkmoney.fistful.client.exception.FistfulException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FistfulClient {

    private final P2PAdapterHostSrv.Iface p2PAdapterHostSrv;

    public ProcessCallbackResult processCallback (Callback callback) {
        log.info("ProcessCallback with callback={}", callback);
        try {
            return p2PAdapterHostSrv.processCallback(callback);
        } catch (TException ex) {
            throw new FistfulException(String.format("Can't extract response from processCallback, callback=%s", callback), ex);
        }
    }

}
