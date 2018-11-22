package com.rbkmoney.cds.client.storage;

import com.rbkmoney.cds.client.storage.exception.CdsStorageException;
import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.SessionData;
import com.rbkmoney.damsel.cds.StorageSrv;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.DisposablePaymentResource;
import com.rbkmoney.damsel.proxy_provider.PaymentContext;
import com.rbkmoney.damsel.proxy_provider.PaymentResource;
import com.rbkmoney.damsel.proxy_provider.RecurrentPaymentResource;
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

    public CardData getCardData(String token) {
        log.info("Get card data by token: {}", token);
        try {
            return storageSrv.getCardData(token);
        } catch (TException ex) {
            throw new CdsStorageException(String.format("Can't get card data with token: %s", token), ex);
        }
    }

    public CardData getCardData(PaymentContext context) {
        return getCardData(getBankCardToken(getPaymentResource(context)));
    }

    public CardData getCardData(Withdrawal withdrawal) {
        Optional<String> token = Optional.ofNullable(withdrawal.getDestination())
                .map(Destination::getBankCard)
                .map(BankCard::getToken);

        if (!token.isPresent()) {
            throw new CdsStorageException("Token must be set for card data, withdrawalId " + withdrawal.getId());
        }

        return getCardData(token.get());
    }

    public SessionData getSessionData(PaymentContext context) {
        DisposablePaymentResource disposablePaymentResource = getDisposablePaymentResource(context);
        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            String invoiceId = getInvoiceId(context);
            throw new CdsStorageException("Session must be set for session data, invoiceId " + invoiceId);
        }
        return getSessionDataBySessionId(disposablePaymentResource.getPaymentSessionId());
    }

    public CardData getCardData(RecurrentTokenContext context) {
        DisposablePaymentResource disposablePaymentResource = getDisposablePaymentResource(context);
        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            String recurrentId = getRecurrentId(context);
            throw new CdsStorageException("Session Id must be set, recurrentId " + recurrentId);
        }
        String token = disposablePaymentResource.getPaymentTool().getBankCard().getToken();
        return getCardData(token);
    }

    public SessionData getSessionData(RecurrentTokenContext context) {
        DisposablePaymentResource disposablePaymentResource = getDisposablePaymentResource(context);
        if (!disposablePaymentResource.isSetPaymentSessionId()) {
            String recurrentId = getRecurrentId(context);
            throw new CdsStorageException("Session Id must be set, recurrentId " + recurrentId);
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

    private static DisposablePaymentResource getDisposablePaymentResource(RecurrentTokenContext context) {
        return context.getTokenInfo().getPaymentTool().getPaymentResource();
    }

    private static DisposablePaymentResource getDisposablePaymentResource(PaymentContext context) {
        return context.getPaymentInfo().getPayment().getPaymentResource().getDisposablePaymentResource();
    }

    private static PaymentResource getPaymentResource(PaymentContext context) {
        return context.getPaymentInfo().getPayment().getPaymentResource();
    }

    private static String getRecurrentId(RecurrentTokenContext context) {
        return context.getTokenInfo().getPaymentTool().getId();
    }

    private static String getInvoiceId(PaymentContext context) {
        return context.getPaymentInfo().getInvoice().getId();
    }

    private static String getBankCardToken(PaymentResource paymentResource) {
        if (paymentResource.isSetDisposablePaymentResource()) {
            return getBankCardToken(paymentResource.getDisposablePaymentResource());
        }
        return getBankCardToken(paymentResource.getRecurrentPaymentResource());
    }

    private static String getBankCardToken(RecurrentPaymentResource paymentResource) {
        return paymentResource.getPaymentTool().getBankCard().getToken();
    }

    private static String getBankCardToken(DisposablePaymentResource paymentResource) {
        return paymentResource.getPaymentTool().getBankCard().getToken();
    }
}
