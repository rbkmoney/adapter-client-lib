package com.rbkmoney.adapter.helpers.hellgate;

import com.rbkmoney.adapter.helpers.hellgate.exception.HellgateException;
import com.rbkmoney.damsel.proxy_provider.ProviderProxyHostSrv;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
public class HellgateAdapterClientTest {

    private String someTag = "some_tag";
    private ByteBuffer someCallback = ByteBuffer.wrap("some_byte".getBytes());
    private ByteBuffer response = ByteBuffer.wrap("some_response_byte".getBytes());

    @Mock
    private ProviderProxyHostSrv.Iface providerProxyHostSrv;
    private HellgateAdapterClient hellgateAdapterClient;

    @Before
    public void setUp() {
        initMocks(HellgateAdapterClientTest.class);
        hellgateAdapterClient = new HellgateAdapterClient(providerProxyHostSrv);
    }

    @Test
    public void testHellgateWorksOnProcessCallback() throws Exception {
        Mockito.when(providerProxyHostSrv.processPaymentCallback(someTag, someCallback)).thenReturn(response);

        assertEquals(response, hellgateAdapterClient.processPaymentCallback(someTag, someCallback));
        verify(providerProxyHostSrv, times(1)).processPaymentCallback(eq(someTag), eq(someCallback));
    }

    @Test
    public void testHellgateWorksOnProcessRecurrentTokenCallback() throws Exception {
        Mockito.when(providerProxyHostSrv.processRecurrentTokenCallback(someTag, someCallback)).thenReturn(response);

        assertEquals(response, hellgateAdapterClient.processRecurrentTokenCallback(someTag, someCallback));
        verify(providerProxyHostSrv, times(1)).processRecurrentTokenCallback(eq(someTag), eq(someCallback));
    }

    @Test(expected = HellgateException.class)
    public void testHellgateReturnsTExceptionOnProcessAnyCallback() throws Exception {
        Mockito.when(providerProxyHostSrv.processPaymentCallback(someTag, someCallback)).thenThrow(new TException());

        hellgateAdapterClient.processPaymentCallback(someTag, someCallback);
    }

}
