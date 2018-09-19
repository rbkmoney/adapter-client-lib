package com.rbkmoney.hellgate.client.proxy.host.provider;


import com.rbkmoney.damsel.proxy_provider.ProviderProxyHostSrv;
import com.rbkmoney.hellgate.client.proxy.host.provider.exception.HellgateProxyHostProviderException;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

@Component
public class HellgateClientProxyHostProvider {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ProviderProxyHostSrv.Iface providerProxyHostSrv;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link HellgateClientProxyHostProvider} instance.
     */
    public HellgateClientProxyHostProvider() {
        // Constructs default a new {@link HellgateClientProxyHostProvider} instance.
    }

    /**
     * Constructs a new {@link HellgateClientProxyHostProvider} instance with the given
     * initial parameters to be constructed.
     *
     * @param providerProxyHostSrv the field's providerProxyHostSrv (see {@link #providerProxyHostSrv}).
     */
    @Autowired
    public HellgateClientProxyHostProvider(ProviderProxyHostSrv.Iface providerProxyHostSrv) {
        this.providerProxyHostSrv = providerProxyHostSrv;
    }


    // ------------------------------------------------------------------------
    // Public methods
    // ------------------------------------------------------------------------

    public ByteBuffer processPaymentCallback(String tag, ByteBuffer callback) {
        log.info("processPaymentCallback start with tag {}", tag);
        try {
            ByteBuffer callbackResponse = providerProxyHostSrv.processPaymentCallback(tag, callback);
            log.info("processPaymentCallback finish with tag {}", tag);
            return callbackResponse;
        } catch (TException ex) {
            throw new HellgateProxyHostProviderException(String.format("Exception in processPaymentCallback with tag: %s", tag), ex);
        }
    }

    public ByteBuffer processRecurrentTokenCallback(String tag, ByteBuffer callback) {
        log.info("processRecurrentTokenCallback start with tag {}", tag);
        try {
            ByteBuffer callbackResponse = providerProxyHostSrv.processRecurrentTokenCallback(tag, callback);
            log.info("processRecurrentTokenCallback finish with tag {}", tag);
            return callbackResponse;
        } catch (TException ex) {
            throw new HellgateProxyHostProviderException(String.format("Exception in processRecurrentTokenCallback with tag: %s", tag), ex);
        }
    }

}
