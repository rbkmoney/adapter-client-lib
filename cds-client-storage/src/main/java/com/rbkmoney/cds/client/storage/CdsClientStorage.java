package com.rbkmoney.cds.client.storage;

import com.rbkmoney.cds.client.storage.exception.CdsStorageException;
import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.PutCardDataResult;
import com.rbkmoney.damsel.cds.SessionData;
import com.rbkmoney.damsel.cds.StorageSrv;
import com.rbkmoney.damsel.domain.DisposablePaymentResource;
import com.rbkmoney.damsel.proxy_provider.InvoicePayment;
import com.rbkmoney.damsel.proxy_provider.PaymentContext;
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenContext;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CdsClientStorage {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private StorageSrv.Iface storageSrv;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link CdsClientStorage} instance.
     */
    public CdsClientStorage() {
        // Constructs default a new {@link CdsClientStorage} instance.
    }

    /**
     * Constructs a new {@link CdsClientStorage} instance with the given
     * initial parameters to be constructed.
     *
     * @param storageSrv the field's storageSrv (see {@link #storageSrv}).
     */
    @Autowired
    public CdsClientStorage(StorageSrv.Iface storageSrv) {
        this.storageSrv = storageSrv;
    }


    // ------------------------------------------------------------------------
    // Public methods
    // ------------------------------------------------------------------------

    /**
     * Get the card data without CVV
     *
     * @param token String
     * @return CardData
     */
    public CardData getCardData(final String token) {
        log.info("getCardData: token: {}", token);
        try {
            CardData cardData = storageSrv.getCardData(token);
            log.info("getCardData: response, token: {}");
            return cardData;
        } catch (Exception ex) {
            throw new CdsStorageException(String.format("Exception in getCardData with token: %s", token), ex);
        }
    }

    public CardData getCardData(final PaymentContext context) {
        String invoiceId = context.getPaymentInfo().getInvoice().getId();

        InvoicePayment invoicePayment = context.getPaymentInfo().getPayment();
        DisposablePaymentResource disposablePaymentResource = invoicePayment.getPaymentResource().getDisposablePaymentResource();

        if (!disposablePaymentResource.getPaymentTool().getBankCard().isSetToken()) {
            throw new CdsStorageException("getCardData: Token must be set, invoiceId " + invoiceId);
        }

        String token = disposablePaymentResource.getPaymentTool().getBankCard().getToken();
        return getCardData(token);
    }

    public CardData getCardData(final Withdrawal withdrawal) {
        String withdrawalId = withdrawal.getId();

        String token = null;
        if(withdrawal.isSetDestination()) {
            if(withdrawal.getDestination().isSetBankCard()) {
                if(withdrawal.getDestination().getBankCard().isSetToken()) {
                    token = withdrawal.getDestination().getBankCard().getToken();
                }
            }
        }


        if (token == null) {
            throw new CdsStorageException("getCardData: Token must be set, withdrawalId " + withdrawalId);
        }

        return getCardData(token);
    }

    public SessionData getSessionData(final PaymentContext context) {
        String invoiceId = context.getPaymentInfo().getInvoice().getId();

        InvoicePayment invoicePayment = context.getPaymentInfo().getPayment();
        DisposablePaymentResource disposablePaymentResource = invoicePayment.getPaymentResource().getDisposablePaymentResource();

        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            throw new CdsStorageException("getSessionData: Session must be set, invoiceId " + invoiceId);
        }

        String session = disposablePaymentResource.getPaymentSessionId();
        try {
            SessionData sessionData = storageSrv.getSessionData(session);
            log.info("Storage getSessionData: finish");
            return sessionData;
        } catch (Exception ex) {
            throw new CdsStorageException("Exception in getSessionData with SessionData", ex);
        }
    }

    public CardData getCardData(final RecurrentTokenContext context) {
        String recurrentId = context.getTokenInfo().getPaymentTool().getId();
        DisposablePaymentResource disposablePaymentResource = context.getTokenInfo().getPaymentTool().getPaymentResource();

        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            throw new CdsStorageException("getSessionData: Session must be set, recurrentId " + recurrentId);
        }

        String token = disposablePaymentResource.getPaymentTool().getBankCard().getToken();
        return getCardData(token);
    }

    public SessionData getSessionData(final RecurrentTokenContext context) {
        String recurrentId = context.getTokenInfo().getPaymentTool().getId();
        DisposablePaymentResource disposablePaymentResource = context.getTokenInfo().getPaymentTool().getPaymentResource();

        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            throw new CdsStorageException("getSessionData: Session must be set, recurrentId " + recurrentId);
        }

        String session = disposablePaymentResource.getPaymentSessionId();
        try {
            SessionData sessionData = storageSrv.getSessionData(session);
            log.info("Storage getSessionData: finish");
            return sessionData;
        } catch (Exception ex) {
            throw new CdsStorageException("Exception in getSessionData with SessionData", ex);
        }
    }

    /**
     * Put the card data
     *
     * @param cardData CardData
     * @return PutCardDataResult
     */
    public PutCardDataResult putCardData(CardData cardData, SessionData sessionData) throws CdsStorageException {
        log.info("Storage putCardData - start");
        try {
            PutCardDataResult result = storageSrv.putCardData(cardData, sessionData);
            log.info("Storage putCardData: finish");
            return result;
        } catch (Exception ex) {
            throw new CdsStorageException("Exception in putCardData with cardData", ex);
        }
    }

}
