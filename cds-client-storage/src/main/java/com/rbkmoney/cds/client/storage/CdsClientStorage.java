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
        LOGGER.info("Storage getCardData start with token {}", token);
        CardData cardData = storageSrv.getCardData(token);
        LOGGER.info("Storage getCardData finish");
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
        LOGGER.info("Storage getSessionCardData start with token {}, session{} ", token, session);
        CardData cardData = storageSrv.getSessionCardData(token, session);
        LOGGER.info("Storage getSessionCardData finish");
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
        LOGGER.info("Storage putCardData start");
        PutCardDataResult result = storageSrv.putCardData(cardData);
        LOGGER.info("Storage putCardData: finish");
        return result;
    }

}
