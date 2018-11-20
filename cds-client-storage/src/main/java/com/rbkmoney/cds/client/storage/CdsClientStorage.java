package com.rbkmoney.cds.client.storage;

import com.rbkmoney.cds.client.storage.exception.CdsStorageException;
import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.PutCardDataResult;
import com.rbkmoney.damsel.cds.SessionData;
import com.rbkmoney.damsel.cds.StorageSrv;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.DisposablePaymentResource;
import com.rbkmoney.damsel.proxy_provider.InvoicePayment;
import com.rbkmoney.damsel.proxy_provider.PaymentContext;
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenContext;
import com.rbkmoney.damsel.withdrawals.domain.Destination;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CdsClientStorage {

    private final StorageSrv.Iface storageSrv;

    public CardData getCardData(final String token) {
        log.info("getCardData: token: {}", token);
        try {
            CardData cardData = storageSrv.getCardData(token);
            log.info("getCardData: response, token: {}", token);
            return cardData;
        } catch (TException ex) {
            throw new CdsStorageException(String.format("Can't get card data with token: %s", token), ex);
        }
    }

    public CardData getCardData(final PaymentContext context) {
        InvoicePayment invoicePayment = context.getPaymentInfo().getPayment();
        String token;
        if (invoicePayment.getPaymentResource().isSetDisposablePaymentResource()) {
            token = invoicePayment.getPaymentResource().getDisposablePaymentResource().getPaymentTool().getBankCard().getToken();
        } else {
            token = invoicePayment.getPaymentResource().getRecurrentPaymentResource().getPaymentTool().getBankCard().getToken();
        }
        return getCardData(token);
    }

    public CardData getCardData(final Withdrawal withdrawal) {
        String withdrawalId = withdrawal.getId();

        Optional<String> token = Optional.ofNullable(withdrawal.getDestination())
                .map(Destination::getBankCard)
                .map(BankCard::getToken);

        if (!token.isPresent()) {
            throw new CdsStorageException("Token must be set for card data, withdrawalId " + withdrawalId);
        }

        return getCardData(token.get());
    }

    public SessionData getSessionData(final PaymentContext context) {
        String invoiceId = context.getPaymentInfo().getInvoice().getId();

        InvoicePayment invoicePayment = context.getPaymentInfo().getPayment();
        DisposablePaymentResource disposablePaymentResource = invoicePayment.getPaymentResource().getDisposablePaymentResource();

        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            throw new CdsStorageException("Session must be set for session data, invoiceId " + invoiceId);
        }

        return getSessionDataBySessionId(disposablePaymentResource.getPaymentSessionId());
    }

    public CardData getCardData(final RecurrentTokenContext context) {
        String recurrentId = context.getTokenInfo().getPaymentTool().getId();
        DisposablePaymentResource disposablePaymentResource = context.getTokenInfo().getPaymentTool().getPaymentResource();

        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            throw new CdsStorageException("Session Id must be set, recurrentId " + recurrentId);
        }

        String token = disposablePaymentResource.getPaymentTool().getBankCard().getToken();
        return getCardData(token);
    }

    public SessionData getSessionData(final RecurrentTokenContext context) {
        String recurrentId = context.getTokenInfo().getPaymentTool().getId();
        DisposablePaymentResource disposablePaymentResource = context.getTokenInfo().getPaymentTool().getPaymentResource();

        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            throw new CdsStorageException("Session Id must be set, recurrentId " + recurrentId);
        }

        return getSessionDataBySessionId(disposablePaymentResource.getPaymentSessionId());
    }

    public SessionData getSessionDataBySessionId(String sessionId) {
        try {
            SessionData sessionData = storageSrv.getSessionData(sessionId);
            log.info("Storage getSessionData: finish");
            return sessionData;
        } catch (TException ex) {
            throw new CdsStorageException("Can't get session data by session Id " + sessionId, ex);
        }
    }

    public PutCardDataResult putCardData(CardData cardData, SessionData sessionData) throws CdsStorageException {
        log.info("Storage putCardData - start");
        try {
            PutCardDataResult result = storageSrv.putCardData(cardData, sessionData);
            log.info("Storage putCardData: finish");
            return result;
        } catch (TException ex) {
            throw new CdsStorageException("Can't put card data", ex);
        }
    }

}
