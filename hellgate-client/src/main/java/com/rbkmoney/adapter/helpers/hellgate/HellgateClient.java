package com.rbkmoney.adapter.helpers.hellgate;


import com.rbkmoney.adapter.helpers.hellgate.exception.HellgateException;
import com.rbkmoney.damsel.proxy_provider.ProviderProxyHostSrv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

@Component
@Slf4j
@RequiredArgsConstructor
public class HellgateClient {

    private final ProviderProxyHostSrv.Iface providerProxyHostSrv;

    public ByteBuffer processPaymentCallback(String tag, ByteBuffer callback) {
        log.info("processPaymentCallback start with tag {}", tag);
        try {
            ByteBuffer callbackResponse = providerProxyHostSrv.processPaymentCallback(tag, callback);
            log.info("processPaymentCallback finish with tag {}", tag);
            return callbackResponse;
        } catch (TException ex) {
            throw new HellgateException(String.format("Exception in processPaymentCallback with tag: %s", tag), ex);
        }
    }

    public ByteBuffer processRecurrentTokenCallback(String tag, ByteBuffer callback) {
        log.info("processRecurrentTokenCallback start with tag {}", tag);
        try {
            ByteBuffer callbackResponse = providerProxyHostSrv.processRecurrentTokenCallback(tag, callback);
            log.info("processRecurrentTokenCallback finish with tag {}", tag);
            return callbackResponse;
        } catch (TException ex) {
            throw new HellgateException(String.format("Exception in processRecurrentTokenCallback with tag: %s", tag), ex);
        }
    }

}
