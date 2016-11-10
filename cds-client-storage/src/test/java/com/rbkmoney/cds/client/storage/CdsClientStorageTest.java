package com.rbkmoney.cds.client.storage;

import com.rbkmoney.damsel.base.Ok;
import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.ExpDate;
import com.rbkmoney.damsel.cds.PutCardDataResult;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardPaymentSystem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class CdsClientStorageTest {

    @Mock
    private CdsClientStorage client;

    @Mock
    private Ok ok;

    @Mock
    private CardData cardData;

    @Mock
    private BankCard bankCard;

    @Mock
    private PutCardDataResult putCardDataResult;

    String token = "caIuPt1KQLQ2g5qh8pK9wg";
    String session = "some_session_123";

    @Before
    public void setUp() throws Exception {
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under test.
        MockitoAnnotations.initMocks(this);

        cardData = makeCardData();
        bankCard = makeBankCard();

        putCardDataResult.setBankCard(makeBankCard());
        putCardDataResult.setSession(session);
    }

    @Test
    public void testGetCardData() throws Exception {
        Mockito.when(client.getCardData(token)).thenReturn(cardData);

        assertEquals(cardData, client.getCardData(token));
    }

    @Test
    public void testGetSessionCardData() throws Exception {
        Mockito.when(client.getSessionCardData(token, session)).thenReturn(cardData);

        assertEquals(cardData, client.getSessionCardData(token, session));
    }

    @Test
    public void testPutCardData() throws Exception {
        Mockito.when(client.putCardData(cardData)).thenReturn(putCardDataResult);

        assertEquals(putCardDataResult, client.putCardData(cardData));
    }

    private ExpDate makeExpDate() {
        byte month = Byte.parseByte("12");
        short year = Short.parseShort("2023");

        ExpDate expDate = new ExpDate();
        expDate.setMonth(month);
        expDate.setYear(year);

        return expDate;
    }

    private CardData makeCardData() {
        CardData cardData = new CardData();
        cardData.setCardholderName("NONAME");
        cardData.setCvv("123");
        cardData.setPan("4300000000000777");
        cardData.setExpDate(makeExpDate());
        return cardData;
    }

    private BankCard makeBankCard() {
        BankCard bankCard = new BankCard();
        bankCard.setBin("123456");
        bankCard.setMaskedPan("1234****4321");
        bankCard.setToken(token);
        bankCard.setPaymentSystem(BankCardPaymentSystem.visa);
        return bankCard;
    }

}