package com.rbkmoney.damsel;


import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.ExpDate;
import com.rbkmoney.damsel.domain.*;
import com.rbkmoney.damsel.proxy_provider.*;
import com.rbkmoney.damsel.proxy_provider.Invoice;
import com.rbkmoney.damsel.proxy_provider.InvoicePayment;
import com.rbkmoney.damsel.proxy_provider.Shop;
import com.rbkmoney.damsel.user_interaction.UserInteraction;

import java.nio.ByteBuffer;
import java.util.Map;


/**
 * @author Anatoly Cherkasov
 */
public class ProxyProviderWrapper {

    public static final String DEFAULT_ERROR_CODE = "error";


    public static TargetInvoicePaymentStatus makeTargetProcessed() {
        return TargetInvoicePaymentStatus.processed(new InvoicePaymentProcessed());
    }

    public static TargetInvoicePaymentStatus makeTargetCaptured() {
        return TargetInvoicePaymentStatus.captured(new InvoicePaymentCaptured());
    }

    public static TargetInvoicePaymentStatus makeTargetCancelled() {
        return TargetInvoicePaymentStatus.cancelled(new InvoicePaymentCancelled());
    }

    public static TargetInvoicePaymentStatus makeTargetRefunded() {
        return TargetInvoicePaymentStatus.refunded(new InvoicePaymentRefunded());
    }

    public static Session makeSession(TargetInvoicePaymentStatus target, byte[] state) {
        return new Session(target).setState(state);
    }

    public static Session makeSession(TargetInvoicePaymentStatus target) {
        return ProxyProviderWrapper.makeSession(target, null);
    }


    // RecurrentTokenIntent
    public static RecurrentTokenSuccess makeRecurrentTokenSuccess(String token) {
        return new RecurrentTokenSuccess(token);
    }

    public static RecurrentTokenFinishIntent makeRecurrentTokenStatusSuccess(String token) {
        return new RecurrentTokenFinishIntent(RecurrentTokenFinishStatus.success(makeRecurrentTokenSuccess(token)));
    }

    public static RecurrentTokenFinishIntent makeRecurrentTokenStatusFailure(Failure failure) {
        return new RecurrentTokenFinishIntent(RecurrentTokenFinishStatus.failure(failure));
    }

    public static RecurrentTokenIntent makeRecurrentTokenFinishIntentFailure(Failure failure) {
        return RecurrentTokenIntent.finish(makeRecurrentTokenStatusFailure(failure));
    }

    public static RecurrentTokenIntent makeRecurrentTokenFinishIntentSuccess(String token) {
        return RecurrentTokenIntent.finish(makeRecurrentTokenStatusSuccess(token));
    }

    public static RecurrentTokenIntent makeRecurrentTokenWithSuspendIntent(String tag, int timer, UserInteraction userInteraction) {
        return RecurrentTokenIntent.suspend(ProxyWrapper.makeSuspendIntent(tag, timer, userInteraction));
    }

    public static RecurrentTokenIntent makeRecurrentTokenWithSuspendIntent(String tag, int timer) {
        return makeRecurrentTokenWithSuspendIntent(tag, timer, null);
    }

    // RecurrentTokenInfo
    public static RecurrentTokenInfo makeRecurrentTokenInfo(RecurrentPaymentTool recurrentPaymentTool) {
        return new RecurrentTokenInfo(recurrentPaymentTool);
    }

    // DisposablePaymentResource
    public static DisposablePaymentResource makeDisposablePaymentResource(String sessionId, PaymentTool paymentTool) {
        return new DisposablePaymentResource().setPaymentSessionId(sessionId).setPaymentTool(paymentTool);
    }

    // RecurrentPaymentTool
    public static RecurrentPaymentTool makeRecurrentPaymentTool(DisposablePaymentResource disposablePaymentResource) {
        return new RecurrentPaymentTool().setPaymentResource(disposablePaymentResource);
    }

    public static RecurrentPaymentTool makeRecurrentPaymentTool(DisposablePaymentResource disposablePaymentResource, com.rbkmoney.damsel.proxy_provider.Cash cash) {
        return new RecurrentPaymentTool().setPaymentResource(disposablePaymentResource).setMinimalPaymentCost(cash);
    }

    public static RecurrentPaymentTool makeRecurrentPaymentTool(String id, DisposablePaymentResource disposablePaymentResource, com.rbkmoney.damsel.proxy_provider.Cash cash) {
        return new RecurrentPaymentTool().setPaymentResource(disposablePaymentResource).setMinimalPaymentCost(cash).setId(id);
    }


    // RecurrentTokenProxyResult
    public static RecurrentTokenProxyResult makeRecurrentTokenProxyResult(RecurrentTokenIntent intent, byte[] nextState, TransactionInfo trx) {
        return new RecurrentTokenProxyResult(intent).setNextState(nextState).setTrx(trx);
    }

    public static RecurrentTokenProxyResult makeRecurrentTokenProxyResult(RecurrentTokenIntent intent) {
        return makeRecurrentTokenProxyResult(intent, null, null);
    }

    public static RecurrentTokenProxyResult makeRecurrentTokenProxyResult(RecurrentTokenIntent intent, byte[] nextState) {
        return makeRecurrentTokenProxyResult(intent, nextState, null);
    }

    public static RecurrentTokenProxyResult makeRecurrentTokenProxyResultFailure(Failure failure) {
        return makeRecurrentTokenProxyResult(makeRecurrentTokenFinishIntentFailure(failure));
    }


    // ProxyResult
    public static PaymentProxyResult makePaymentProxyResult(Intent intent, byte[] next_state, TransactionInfo trx) {
        return new PaymentProxyResult(intent).setNextState(next_state).setTrx(trx);
    }

    public static PaymentProxyResult makePaymentProxyResult(Intent intent, byte[] next_state) {
        return makePaymentProxyResult(intent, next_state, null);
    }

    public static PaymentProxyResult makePaymentProxyResult(Intent intent) {
        return makePaymentProxyResult(intent, null, null);
    }

    public static PaymentProxyResult makeProxyResultFailure(Failure failure) {
        return new PaymentProxyResult(ProxyWrapper.makeFinishIntentFailure(failure));
    }

    public static com.rbkmoney.damsel.domain.Currency makeCurrency(String name, short numericCode, String symbolicCode, short exponent) {
        return DomainWrapper.makeCurrency(name, numericCode, symbolicCode, exponent);
    }

    public static ExpDate makeExpDate(byte month, short year) {
        return CdsWrapper.makeExpDate(month, year);
    }

    public static com.rbkmoney.damsel.proxy_provider.Cash makeCash(com.rbkmoney.damsel.domain.Currency currency, Long amount) {
        return new com.rbkmoney.damsel.proxy_provider.Cash(amount, currency);
    }

    public static com.rbkmoney.damsel.domain.Cash makeCash(CurrencyRef currency, Long amount) {
        return new com.rbkmoney.damsel.domain.Cash(amount, currency);
    }

    public static CardData makeCardData(String cardholderName, String cvv, String pan, ExpDate expDate) {
        return CdsWrapper.makeCardData(cardholderName, cvv, pan, expDate);
    }

    public static BankCard makeBankCard(String bin, String maskedPan, String token, BankCardPaymentSystem bankCardPaymentSystem) {
        return DomainWrapper.makeBankCard(bin, maskedPan, token, bankCardPaymentSystem);
    }

    public static PaymentInfo makePaymentInfo(Invoice invoice, Shop shop, InvoicePayment invoicePayment) {
        return new PaymentInfo(shop, invoice, invoicePayment);
    }

    public static PaymentContext makeContext(PaymentInfo paymentInfo, Session session, Map<String, String> options) {
        return new PaymentContext(session, paymentInfo).setOptions(options);
    }

    public static Shop makeShop(Category category, ShopDetails shopDetails) {
        return new Shop().setCategory(category).setDetails(shopDetails);
    }

    public static Invoice makeInvoice(String invoiceID, String createdAt, com.rbkmoney.damsel.proxy_provider.Cash cost) {
        return new Invoice().setId(invoiceID).setCreatedAt(createdAt).setCost(cost);
    }

    public static InvoicePayment makeInvoicePayment(String invoicePaymentId, String created_at, PaymentResource paymentResource, com.rbkmoney.damsel.proxy_provider.Cash cost) {
        return new InvoicePayment().setId(invoicePaymentId).setCreatedAt(created_at).setPaymentResource(paymentResource).setCost(cost);
    }

    public static PaymentResource makePaymentResourceDisposablePaymentResource(DisposablePaymentResource disposablePaymentResource) {
        PaymentResource paymentResource = new PaymentResource();
        paymentResource.setDisposablePaymentResource(disposablePaymentResource);
        return paymentResource;
    }

    public static RecurrentPaymentResource makeRecurrentPaymentResource(String token) {
        return new RecurrentPaymentResource().setRecToken(token);
    }

    public static PaymentResource makePaymentResourceRecurrentPaymentResource(RecurrentPaymentResource recurrentPaymentResource) {
        return PaymentResource.recurrent_payment_resource(recurrentPaymentResource);
    }

    public static InvoicePayment makeInvoicePaymentWithTrX(String invoicePaymentId, String created_at, PaymentResource paymentResource, com.rbkmoney.damsel.proxy_provider.Cash cost, TransactionInfo transactionInfo) {
        return new InvoicePayment().setId(invoicePaymentId).setCreatedAt(created_at).setPaymentResource(paymentResource).setCost(cost).setTrx(transactionInfo);
    }

    public static Session makeSession(byte[] state) {
        return new Session().setState(state);
    }

    public static Session makeSession(ByteBuffer state) {
        return new Session().setState(state);
    }

    public static PaymentCallbackProxyResult makeCallbackProxyResult(Intent intent, byte[] next_state, TransactionInfo trx) {
        return new PaymentCallbackProxyResult().setIntent(intent).setNextState(next_state).setTrx(trx);
    }

    public static PaymentCallbackProxyResult makeCallbackProxyResultFailure(Failure failure) {
        return new PaymentCallbackProxyResult().setIntent(ProxyWrapper.makeFinishIntentFailure(failure));
    }

    public static PaymentCallbackResult makeCallbackResult(byte[] callbackResponse, PaymentCallbackProxyResult proxyResult) {
        return new PaymentCallbackResult().setResponse(callbackResponse).setResult(proxyResult);
    }

    public static PaymentCallbackResult makeCallbackResultFailure(byte[] callbackResponse, Failure failure) {
        return new PaymentCallbackResult().setResponse(callbackResponse).setResult(ProxyProviderWrapper.makeCallbackProxyResultFailure(failure));
    }

    public static PaymentCallbackResult makeCallbackResultFailure(Failure failure) {
        return makeCallbackResultFailure(DEFAULT_ERROR_CODE.getBytes(), failure);
    }

    // RecurrentTokenCallbackResult
    public static RecurrentTokenCallbackResult makeRecurrentTokenCallbackResult(byte[] callbackResponse, RecurrentTokenProxyResult proxyResult) {
        return new RecurrentTokenCallbackResult().setResponse(callbackResponse).setResult(proxyResult);
    }

    public static RecurrentTokenCallbackResult makeRecurrentTokenCallbackResultFailure(byte[] callbackResponse, Failure failure) {
        return new RecurrentTokenCallbackResult().setResponse(callbackResponse).setResult(makeRecurrentTokenProxyResult(makeRecurrentTokenFinishIntentFailure(failure)));
    }

    public static RecurrentTokenCallbackResult makeRecurrentTokenCallbackResultFailure(Failure failure) {
        return makeRecurrentTokenCallbackResultFailure(DEFAULT_ERROR_CODE.getBytes(), failure);
    }

}
