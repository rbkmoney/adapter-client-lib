package com.rbkmoney.cds.client.storage.utils;

import com.rbkmoney.cds.client.storage.exception.CdsStorageExpDateException;
import com.rbkmoney.cds.storage.CardData;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.java.cds.utils.model.CardDataProxyModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankCardExtractor {

    private static final String UNKNOWN = "UNKNOWN";

    public static CardDataProxyModel initCardDataProxyModel(BankCard bankCard, CardData cardData) {
        String cardHolder = UNKNOWN;
        if (bankCard.isSetCardholderName()) {
            cardHolder = bankCard.getCardholderName();
        } else if (cardData.isSetCardholderName()) {
            cardHolder = cardData.getCardholderName();
        }

        if (!bankCard.isSetExpDate() && !cardData.isSetExpDate()) {
            throw new CdsStorageExpDateException("Expiration date not found");
        }

        return CardDataProxyModel.builder()
                .cardholderName(cardHolder)
                .pan(cardData.getPan())
                .expMonth(bankCard.isSetExpDate() ? bankCard.getExpDate().getMonth() : cardData.getExpDate().getMonth())
                .expYear(bankCard.isSetExpDate() ? bankCard.getExpDate().getYear() : cardData.getExpDate().getYear())
                .build();
    }

}
