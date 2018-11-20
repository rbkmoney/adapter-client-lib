package com.rbkmoney.cds.client.storage;

import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.PutCardDataResult;
import com.rbkmoney.damsel.cds.SessionData;
import com.rbkmoney.damsel.proxy_provider.PaymentContext;
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenContext;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CdsClientStorageTest {

    @Mock
    private PaymentContext paymentContext;

    @Mock
    private Withdrawal withdrawal;

    @Mock
    private RecurrentTokenContext recurrentTokenContext;

    @Mock
    private CardData cardData;

    @Mock
    private SessionData sessionData;

    @Mock
    private PutCardDataResult putCardDataResult;

    @Mock
    private CdsClientStorage clientStorage;

    private String token = "some_token";


    @Before
    public void setUp() {
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under test.
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCardData() throws TException {
        Mockito.when(clientStorage.getCardData(token)).thenReturn(cardData);

        assertEquals(cardData, clientStorage.getCardData(token));
    }

    @Test
    public void getCardDataPaymentContext() throws TException {
        Mockito.when(clientStorage.getCardData(paymentContext)).thenReturn(cardData);

        assertEquals(cardData, clientStorage.getCardData(paymentContext));
    }

    @Test
    public void getCardDataWithdrawal() {
        Mockito.when(clientStorage.getCardData(withdrawal)).thenReturn(cardData);

        assertEquals(cardData, clientStorage.getCardData(withdrawal));
    }

    @Test
    public void getSessionData() throws TException {
        Mockito.when(clientStorage.getSessionData(paymentContext)).thenReturn(sessionData);

        assertEquals(sessionData, clientStorage.getSessionData(paymentContext));
    }

    @Test
    public void getCardDataRecurrentTokenContext() {
        Mockito.when(clientStorage.getCardData(recurrentTokenContext)).thenReturn(cardData);

        assertEquals(cardData, clientStorage.getCardData(recurrentTokenContext));
    }

    @Test
    public void getSessionData1() throws TException {
        Mockito.when(clientStorage.getSessionData(recurrentTokenContext)).thenReturn(sessionData);

        assertEquals(sessionData, clientStorage.getSessionData(recurrentTokenContext));
    }

    @Test
    public void getSessionDataBySessionId() {
        Mockito.when(clientStorage.getSessionDataBySessionId(token)).thenReturn(sessionData);

        assertEquals(sessionData, clientStorage.getSessionDataBySessionId(token));
    }

    @Test
    public void putCardData() {

        Mockito.when(clientStorage.putCardData(cardData, sessionData)).thenReturn(putCardDataResult);

        assertEquals(putCardDataResult, clientStorage.putCardData(cardData, sessionData));
    }
}