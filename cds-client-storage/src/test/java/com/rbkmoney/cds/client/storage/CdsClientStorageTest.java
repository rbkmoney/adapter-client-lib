package com.rbkmoney.cds.client.storage;

import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.PutCardDataResult;
import com.rbkmoney.damsel.cds.SessionData;
import com.rbkmoney.damsel.cds.StorageSrv;
import com.rbkmoney.damsel.proxy_provider.PaymentContext;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class CdsClientStorageTest {

    private String token = "some_token";

    private CdsClientStorage client;

    @Mock
    private PutCardDataResult result;

    @Mock
    private PaymentContext paymentContext;

    @Mock
    private CardData cardData;

    @Mock
    private SessionData sessionData;

    @Mock
    private StorageSrv.Iface storageSrv;


    @Before
    public void setUp() {
        initMocks(CdsClientStorageTest.class);
        client = new CdsClientStorage(storageSrv);
    }

    @Test
    public void getCardData() throws TException {
        Mockito.when(storageSrv.getCardData(token)).thenReturn(cardData);

        assertEquals(cardData, client.getCardData(token));
        verify(storageSrv, times(1)).getCardData(eq(token));
    }

    @Test
    public void getSessionData() throws TException {
        Mockito.when(storageSrv.getSessionData(token)).thenReturn(sessionData);

        assertEquals(sessionData, client.getSessionDataBySessionId(token));
        verify(storageSrv, times(1)).getSessionData(eq(token));
    }

    @Test
    public void putCardData() throws TException {
        Mockito.when(storageSrv.putCardData(cardData, sessionData)).thenReturn(result);

        assertEquals(result, client.putCardData(cardData, sessionData));
        verify(storageSrv, times(1)).putCardData(eq(cardData), eq(sessionData));
    }

}