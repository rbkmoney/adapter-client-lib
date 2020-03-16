package com.rbkmoney.cds.client.storage.utils;

import com.rbkmoney.cds.client.storage.model.CardDataProxyModel;
import com.rbkmoney.cds.storage.CardData;
import com.rbkmoney.damsel.domain.BankCard;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankCardExtractor {

    private static final String UNKNOWN = "UNKNOWN";

    public static CardDataProxyModel initCardDataProxyModel(BankCard bankCard, CardData cardData) {
        String cardHolder = UNKNOWN;
        if(bankCard.getCardholderName() != null) {
            cardHolder = bankCard.getCardholderName();
        }

        return CardDataProxyModel.builder()
                .cardholderName(cardHolder)
                .pan(cardData.getPan())
                .expMonth(bankCard.getExpDate().getMonth())
                .expYear(bankCard.getExpDate().getYear())
                .build();
    }

}
