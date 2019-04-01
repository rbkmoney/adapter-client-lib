package com.rbkmoney.cds.client.storage;

import com.rbkmoney.cds.client.storage.exception.CdsStorageException;
import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.SessionData;
import com.rbkmoney.damsel.cds.StorageSrv;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.DisposablePaymentResource;
import com.rbkmoney.damsel.proxy_provider.PaymentContext;
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenContext;
import com.rbkmoney.damsel.withdrawals.domain.Destination;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.rbkmoney.java.damsel.utils.extractors.ProxyProviderPackageExtractors.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class CdsClientStorage {

    private final StorageSrv.Iface storageSrv;

    @Cacheable("cardData")
    public CardData getCardData(String token) {
        log.info("Get card data by token: {}", token);
        try {
            return storageSrv.getCardData(token);
        } catch (TException ex) {
            throw new CdsStorageException(String.format("Can't get card data with token: %s", token), ex);
        }
    }

    public CardData getCardData(PaymentContext context) {
        return getCardData(extractBankCardToken(extractPaymentResource(context)));
    }

    public CardData getCardData(Withdrawal withdrawal) {
        String token = Optional.ofNullable(withdrawal.getDestination())
                .map(Destination::getBankCard)
                .map(BankCard::getToken)
                .orElseThrow(() -> new CdsStorageException("Token must be set for card data, withdrawalId " + withdrawal.getId()));

        return getCardData(token);
    }

    public SessionData getSessionData(PaymentContext context) {
        DisposablePaymentResource disposablePaymentResource = extractDisposablePaymentResource(context);
        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            throw new CdsStorageException("Session must be set for session data, invoiceId " + extractInvoiceId(context));
        }
        return getSessionDataBySessionId(disposablePaymentResource.getPaymentSessionId());
    }

    public CardData getCardData(RecurrentTokenContext context) {
        DisposablePaymentResource disposablePaymentResource = extractDisposablePaymentResource(context);
        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            throw new CdsStorageException("Session Id must be set, recurrentId " + extractRecurrentId(context));
        }
        return getCardData(extractBankCardToken(disposablePaymentResource));
    }

    public SessionData getSessionData(RecurrentTokenContext context) {
        DisposablePaymentResource disposablePaymentResource = extractDisposablePaymentResource(context);
        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            throw new CdsStorageException("Session Id must be set, recurrentId " + extractRecurrentId(context));
        }
        return getSessionDataBySessionId(disposablePaymentResource.getPaymentSessionId());
    }

    public SessionData getSessionDataBySessionId(String sessionId) {
        try {
            return storageSrv.getSessionData(sessionId);
        } catch (TException ex) {
            throw new CdsStorageException("Can't get session data by session Id " + sessionId, ex);
        }
    }
}
