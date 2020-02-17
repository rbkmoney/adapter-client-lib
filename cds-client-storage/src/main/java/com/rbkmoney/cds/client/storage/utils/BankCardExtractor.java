package com.rbkmoney.cds.client.storage.utils;

import com.rbkmoney.cds.client.storage.model.CardDataProxyModel;
import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.domain.BankCard;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankCardExtractor {

    private static final String UNKNOWN = "UNKNOWN";

    public static CardDataProxyModel initCardDataProxyModel(BankCard bankCard, CardData cardData) {
        String cardHolder = UNKNOWN;
        if (cardData.getCardholderName() != null) {
            cardHolder = cardData.getCardholderName();
        } else if (bankCard.getCardholderName() != null) {
            cardHolder = bankCard.getCardholderName();
        }

        return CardDataProxyModel.builder()
                .cardholderName(cardHolder)
                .pan(cardData.getPan())
                .expMonth(cardData.isSetExpDate() ? cardData.getExpDate().month : bankCard.getExpDate().getMonth())
                .expYear(cardData.isSetExpDate() ? cardData.getExpDate().year : bankCard.getExpDate().getYear())
                .build();
    }

}
