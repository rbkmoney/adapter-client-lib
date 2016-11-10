package com.rbkmoney.cds.client.storage;

import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.PutCardDataResult;
import com.rbkmoney.damsel.cds.StorageSrv;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CdsClientStorage {

    private final static Logger LOGGER = LoggerFactory.getLogger(CdsClientStorage.class);

    @Autowired
    private StorageSrv.Iface storageSrv;

    /**
     * Get Card Data Without CVV
     *
     * @param token String
     * @return CardData
     * @throws TException
     */
    public CardData getCardData(String token) throws TException {
        LOGGER.info("Storage getCardData: token {}", token);
        CardData cardData = storageSrv.getCardData(token);
        LOGGER.info("Storage getCardData: response {}", cardData.toString());
        return cardData;
    }

    /**
     * Get Card Data With CVV
     *
     * @param token   String
     * @param session String
     * @return CardData
     * @throws TException
     */
    public CardData getSessionCardData(String token, String session) throws TException {
        LOGGER.info("Storage getSessionCardData: token {}, session{} ", token, session);
        CardData cardData = storageSrv.getSessionCardData(token, session);
        LOGGER.info("Storage getSessionCardData: response {}", cardData.toString());
        return cardData;
    }

    /**
     * Put Card Data
     *
     * @param cardData CardData
     * @return PutCardDataResult
     * @throws TException
     */
    public PutCardDataResult putCardData(CardData cardData) throws TException {
        LOGGER.info("Storage putCardData: cardData {} ", cardData);
        PutCardDataResult result = storageSrv.putCardData(cardData);
        LOGGER.info("Storage putCardData: response {}", result.toString());
        return result;
    }

}
