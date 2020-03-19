package com.rbkmoney.cds.client.storage;

import com.rbkmoney.cds.storage.CardData;
import com.rbkmoney.cds.storage.SessionData;
import com.rbkmoney.cds.storage.StorageSrv;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardExpDate;
import com.rbkmoney.damsel.domain.DisposablePaymentResource;
import com.rbkmoney.damsel.domain.PaymentTool;
import com.rbkmoney.damsel.proxy_provider.InvoicePayment;
import com.rbkmoney.damsel.proxy_provider.PaymentContext;
import com.rbkmoney.damsel.proxy_provider.PaymentInfo;
import com.rbkmoney.damsel.proxy_provider.PaymentResource;
import com.rbkmoney.damsel.withdrawals.domain.Destination;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal;
import com.rbkmoney.java.cds.utils.model.CardDataProxyModel;
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

    private static final String TOKEN = "some_token";
    public static final String CARD_HOLDER_CARD = "CARD_HOLDER_CARD";
    public static final String PAN = "1234123412341234";
    public static final byte MONTH = (byte) 4;
    public static final short YEAR = (short) 20;

    private CdsClientStorage client;

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
        CardData cardData = new CardData();
        cardData.setPan("pan");
        Mockito.when(storageSrv.getCardData(TOKEN)).thenReturn(cardData);

        PaymentContext context = createPaymentContext();
        CardDataProxyModel cardDataProxyModel = client.getCardData(context);
        assertEquals(cardData.getPan(), cardDataProxyModel.getPan());
        verify(storageSrv, times(1)).getCardData(eq(TOKEN));
    }

    @Test
    public void getCardDataWithdrawal() throws TException {
        CardData cardData = new CardData();
        cardData.setPan(PAN);
        Mockito.when(storageSrv.getCardData(TOKEN)).thenReturn(cardData);

        Destination destination = new Destination();
        destination.setBankCard(createBankCard());
        Withdrawal withdrawal = new Withdrawal()
                .setDestination(destination);

        CardDataProxyModel cardDataProxyModel = client.getCardData(withdrawal);
        assertEquals(PAN, cardDataProxyModel.getPan());
        assertEquals(MONTH, cardDataProxyModel.getExpMonth());
        assertEquals(YEAR, cardDataProxyModel.getExpYear());

        verify(storageSrv, times(1)).getCardData(eq(TOKEN));
    }

    private PaymentContext createPaymentContext() {
        PaymentTool paymentTool = new PaymentTool();
        paymentTool.setBankCard(createBankCard());

        PaymentResource paymentResource = new PaymentResource();
        paymentResource.setDisposablePaymentResource(new DisposablePaymentResource().setPaymentTool(paymentTool));
        return new PaymentContext()
                .setPaymentInfo(new PaymentInfo()
                        .setPayment(new InvoicePayment()
                                .setPaymentResource(paymentResource)
                        )
                );
    }

    private BankCard createBankCard() {
        return new BankCard()
                .setToken(TOKEN)
                .setExpDate(new BankCardExpDate()
                        .setMonth(MONTH)
                        .setYear(YEAR))
                .setCardholderName(CARD_HOLDER_CARD);
    }

    @Test
    public void getCardDataEmpty() throws TException {
        CardData cardData = new CardData();
        Mockito.when(storageSrv.getCardData(TOKEN)).thenReturn(cardData);

        PaymentContext context = createPaymentContext();

        CardDataProxyModel cardDataProxyModel = client.getCardData(context);
        assertEquals(CARD_HOLDER_CARD, cardDataProxyModel.getCardholderName());
        verify(storageSrv, times(1)).getCardData(eq(TOKEN));
    }

    @Test
    public void getSessionData() throws TException {
        Mockito.when(storageSrv.getSessionData(TOKEN)).thenReturn(sessionData);

        assertEquals(sessionData, client.getSessionDataBySessionId(TOKEN));
        verify(storageSrv, times(1)).getSessionData(eq(TOKEN));
    }

}