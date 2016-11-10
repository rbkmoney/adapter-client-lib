package com.rbkmoney.hellgate.client.proxy.host.provider;

import com.rbkmoney.damsel.proxy_provider.CallbackResult;
import com.rbkmoney.damsel.proxy_provider.Context;
import com.rbkmoney.damsel.proxy_provider.ProxyResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;

public class HellgateClientProxyHostProviderTest {

    @Mock
    HellgateClientProxyHostProvider client;

    @Mock
    Context context;

    @Mock
    ProxyResult proxyResult;

    @Mock
    CallbackResult callbackResult;

    @Mock
    ByteBuffer byteBuffer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processPayment() throws Exception {
        Mockito.when(client.processPayment(context)).thenReturn(proxyResult);

        assertEquals(proxyResult, client.processPayment(context));
    }

    @Test
    public void handlePaymentCallback() throws Exception {
        Mockito.when(client.handlePaymentCallback(byteBuffer, context)).thenReturn(callbackResult);

        assertEquals(callbackResult, client.handlePaymentCallback(byteBuffer, context));
    }

}
