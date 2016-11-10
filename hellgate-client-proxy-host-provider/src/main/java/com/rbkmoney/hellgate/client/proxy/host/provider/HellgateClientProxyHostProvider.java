package com.rbkmoney.hellgate.client.proxy.host.provider;

import com.rbkmoney.damsel.base.TryLater;
import com.rbkmoney.damsel.proxy_provider.CallbackResult;
import com.rbkmoney.damsel.proxy_provider.Context;
import com.rbkmoney.damsel.proxy_provider.ProviderProxySrv;
import com.rbkmoney.damsel.proxy_provider.ProxyResult;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

@Component
public class HellgateClientProxyHostProvider implements ProviderProxySrv.Iface {

    private final static Logger LOGGER = LoggerFactory.getLogger(HellgateClientProxyHostProvider.class);

    @Autowired
    private ProviderProxySrv.Iface providerProxy;

    @Override
    public ProxyResult processPayment(Context context) throws TryLater, TException {
        LOGGER.info("Hellgate ProviderProxy: processPayment start");
        ProxyResult response = providerProxy.processPayment(context);
        LOGGER.info("Hellgate ProviderProxy: processPayment finish");
        return response;
    }

    @Override
    public CallbackResult handlePaymentCallback(ByteBuffer byteBuffer, Context context) throws TryLater, TException {
        LOGGER.info("Hellgate ProviderProxy: handlePaymentCallback start");
        CallbackResult response = providerProxy.handlePaymentCallback(byteBuffer, context);
        LOGGER.info("Hellgate ProviderProxy: handlePaymentCallback finish");
        return response;
    }
}
