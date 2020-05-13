package com.rbkmoney.cds.client.storage.utils;

import com.rbkmoney.cds.client.storage.TestData;
import com.rbkmoney.cds.client.storage.exception.CdsStorageExpDateException;
import com.rbkmoney.cds.storage.CardData;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.java.cds.utils.model.CardDataProxyModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankCardExtractorTest {

    @Test(expected = CdsStorageExpDateException.class)
    public void getCardDataProxyModelWithoutExpDate() {
        BankCard bankCard = TestData.createBankCard();
        CardData cardData = TestData.createCardData();
        BankCardExtractor.initCardDataProxyModel(bankCard, cardData);
    }

    @Test
    public void getCardDataProxyModelWithExpDate() {
        // Only bank card with exp date
        BankCard bankCard = TestData.createBankCardWithExpDate();
        CardData cardData = TestData.createCardData();
        CardDataProxyModel cardDataProxyModel = BankCardExtractor.initCardDataProxyModel(bankCard, cardData);

        assertEquals(TestData.YEAR, cardDataProxyModel.getExpYear());
        assertEquals(TestData.MONTH, cardDataProxyModel.getExpMonth());

        // Only card data with exp date
        bankCard = TestData.createBankCard();
        cardData = TestData.createCardDataWithExpDate();
        cardDataProxyModel = BankCardExtractor.initCardDataProxyModel(bankCard, cardData);

        assertEquals(TestData.YEAR, cardDataProxyModel.getExpYear());
        assertEquals(TestData.MONTH, cardDataProxyModel.getExpMonth());

        // Only all with exp date
        bankCard = TestData.createBankCardWithExpDate();
        cardData = TestData.createCardDataWithExpDate();
        cardDataProxyModel = BankCardExtractor.initCardDataProxyModel(bankCard, cardData);

        assertEquals(TestData.YEAR, cardDataProxyModel.getExpYear());
        assertEquals(TestData.MONTH, cardDataProxyModel.getExpMonth());
    }

}