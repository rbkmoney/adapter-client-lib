package com.rbkmoney.java.damsel.utils.creators;

import com.rbkmoney.damsel.base.Timer;
import com.rbkmoney.damsel.domain.*;
import com.rbkmoney.damsel.proxy_provider.*;
import com.rbkmoney.damsel.proxy_provider.Invoice;
import com.rbkmoney.damsel.proxy_provider.InvoicePayment;
import com.rbkmoney.damsel.proxy_provider.Shop;
import com.rbkmoney.damsel.user_interaction.BrowserGetRequest;
import com.rbkmoney.damsel.user_interaction.BrowserHTTPRequest;
import com.rbkmoney.damsel.user_interaction.BrowserPostRequest;
import com.rbkmoney.damsel.user_interaction.UserInteraction;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Map;

import static com.rbkmoney.java.damsel.constant.Error.DEFAULT_ERROR_CODE;
import static com.rbkmoney.java.damsel.utils.creators.BasePackageCreators.createTimerTimeout;
import static com.rbkmoney.java.damsel.utils.creators.DomainPackageCreators.createFailure;
import static com.rbkmoney.java.damsel.utils.extractors.ProxyProviderPackageExtractors.extractInvoiceId;
import static com.rbkmoney.java.damsel.utils.extractors.ProxyProviderPackageExtractors.extractPaymentId;

public class ProxyProviderPackageCreators {

    public static final String INVOICE_PAYMENT_SEPARATOR_POINT = ".";

    public static String createInvoiceWithPayment(PaymentInfo paymentInfo, String separator) {
        return extractInvoiceId(paymentInfo) + separator + extractPaymentId(paymentInfo);
    }

    public static String createInvoiceWithPayment(PaymentInfo paymentInfo) {
        return createInvoiceWithPayment(paymentInfo, INVOICE_PAYMENT_SEPARATOR_POINT);
    }

    public static Session createSession(TargetInvoicePaymentStatus target, byte[] state) {
        return new Session(target).setState(state);
    }

    public static Session createSession(TargetInvoicePaymentStatus target) {
        return createSession(target, null);
    }

    // RecurrentTokenIntent
    public static RecurrentTokenSuccess createRecurrentTokenSuccess(String token) {
        return new RecurrentTokenSuccess(token);
    }

    public static RecurrentTokenFinishIntent createRecurrentTokenStatusSuccess(String token) {
        return new RecurrentTokenFinishIntent(RecurrentTokenFinishStatus.success(createRecurrentTokenSuccess(token)));
    }

    public static RecurrentTokenFinishIntent createRecurrentTokenStatusFailure(Failure failure) {
        return new RecurrentTokenFinishIntent(RecurrentTokenFinishStatus.failure(failure));
    }

    public static RecurrentTokenIntent createRecurrentTokenFinishIntentFailure(Failure failure) {
        return RecurrentTokenIntent.finish(createRecurrentTokenStatusFailure(failure));
    }

    public static RecurrentTokenIntent createRecurrentTokenFinishIntentSuccess(String token) {
        return RecurrentTokenIntent.finish(createRecurrentTokenStatusSuccess(token));
    }

    public static RecurrentTokenIntent createRecurrentTokenWithSuspendIntent(String tag, int timer, UserInteraction userInteraction) {
        return RecurrentTokenIntent.suspend(createSuspendIntent(tag, timer, userInteraction));
    }

    public static RecurrentTokenIntent createRecurrentTokenWithSuspendIntent(String tag, int timer) {
        return createRecurrentTokenWithSuspendIntent(tag, timer, null);
    }

    // RecurrentTokenInfo
    public static RecurrentTokenInfo createRecurrentTokenInfo(RecurrentPaymentTool recurrentPaymentTool) {
        return new RecurrentTokenInfo(recurrentPaymentTool);
    }

    // DisposablePaymentResource
    public static DisposablePaymentResource createDisposablePaymentResource(String sessionId, PaymentTool paymentTool) {
        return new DisposablePaymentResource().setPaymentSessionId(sessionId).setPaymentTool(paymentTool);
    }

    // RecurrentPaymentTool
    public static RecurrentPaymentTool createRecurrentPaymentTool(DisposablePaymentResource disposablePaymentResource) {
        return new RecurrentPaymentTool().setPaymentResource(disposablePaymentResource);
    }

    public static RecurrentPaymentTool createRecurrentPaymentTool(DisposablePaymentResource disposablePaymentResource, com.rbkmoney.damsel.proxy_provider.Cash cash) {
        return new RecurrentPaymentTool().setPaymentResource(disposablePaymentResource).setMinimalPaymentCost(cash);
    }

    public static RecurrentPaymentTool createRecurrentPaymentTool(String id, DisposablePaymentResource disposablePaymentResource, com.rbkmoney.damsel.proxy_provider.Cash cash) {
        return new RecurrentPaymentTool().setPaymentResource(disposablePaymentResource).setMinimalPaymentCost(cash).setId(id).setCreatedAt(Instant.now().toString());
    }

    // RecurrentTokenProxyResult
    public static RecurrentTokenProxyResult createRecurrentTokenProxyResult(RecurrentTokenIntent intent, byte[] nextState, TransactionInfo trx) {
        return new RecurrentTokenProxyResult(intent).setNextState(nextState).setTrx(trx);
    }

    public static RecurrentTokenProxyResult createRecurrentTokenProxyResult(RecurrentTokenIntent intent) {
        return createRecurrentTokenProxyResult(intent, null, null);
    }

    public static RecurrentTokenProxyResult createRecurrentTokenProxyResult(RecurrentTokenIntent intent, byte[] nextState) {
        return createRecurrentTokenProxyResult(intent, nextState, null);
    }

    public static RecurrentTokenProxyResult createRecurrentTokenProxyResultFailure(Failure failure) {
        return createRecurrentTokenProxyResult(createRecurrentTokenFinishIntentFailure(failure));
    }

    // ProxyResult
    public static PaymentProxyResult createPaymentProxyResult(Intent intent, byte[] next_state, TransactionInfo trx) {
        return new PaymentProxyResult(intent).setNextState(next_state).setTrx(trx);
    }

    public static PaymentProxyResult createPaymentProxyResult(Intent intent, byte[] next_state) {
        return createPaymentProxyResult(intent, next_state, null);
    }

    public static PaymentProxyResult createPaymentProxyResult(Intent intent) {
        return createPaymentProxyResult(intent, null, null);
    }

    public static PaymentProxyResult createProxyResultFailure(Failure failure) {
        return new PaymentProxyResult(createFinishIntentFailure(failure));
    }

    public static PaymentInfo createPaymentInfo(Invoice invoice, Shop shop, InvoicePayment invoicePayment) {
        return new PaymentInfo(shop, invoice, invoicePayment);
    }

    public static PaymentContext createContext(PaymentInfo paymentInfo, Session session, Map<String, String> options) {
        return new PaymentContext(session, paymentInfo).setOptions(options);
    }

    public static PaymentResource createPaymentResourceDisposablePaymentResource(DisposablePaymentResource disposablePaymentResource) {
        PaymentResource paymentResource = new PaymentResource();
        paymentResource.setDisposablePaymentResource(disposablePaymentResource);
        return paymentResource;
    }

    public static RecurrentPaymentResource createRecurrentPaymentResource(String token) {
        return new RecurrentPaymentResource().setRecToken(token);
    }

    public static PaymentResource createPaymentResourceRecurrentPaymentResource(RecurrentPaymentResource recurrentPaymentResource) {
        return PaymentResource.recurrent_payment_resource(recurrentPaymentResource);
    }

    public static InvoicePayment createInvoicePaymentWithTrX(String invoicePaymentId, String createdAt, PaymentResource paymentResource, com.rbkmoney.damsel.proxy_provider.Cash cost, TransactionInfo transactionInfo) {
        return new InvoicePayment().setId(invoicePaymentId).setCreatedAt(createdAt).setPaymentResource(paymentResource).setCost(cost).setTrx(transactionInfo);
    }

    public static Invoice createInvoice(String invoicePaymentId, String createdAt, com.rbkmoney.damsel.proxy_provider.Cash cost) {
        return new Invoice().setId(invoicePaymentId).setCreatedAt(createdAt).setCost(cost);
    }

    public static Session createSession(byte[] state) {
        return new Session().setState(state);
    }

    public static Session createSession(ByteBuffer state) {
        return new Session().setState(state);
    }

    public static PaymentCallbackProxyResult createCallbackProxyResult(Intent intent, byte[] nextState, TransactionInfo trx) {
        return new PaymentCallbackProxyResult().setIntent(intent).setNextState(nextState).setTrx(trx);
    }

    public static PaymentCallbackProxyResult createCallbackProxyResultFailure(Failure failure) {
        return new PaymentCallbackProxyResult().setIntent(createFinishIntentFailure(failure));
    }

    public static PaymentCallbackResult createCallbackResult(byte[] callbackResponse, PaymentCallbackProxyResult proxyResult) {
        return new PaymentCallbackResult().setResponse(callbackResponse).setResult(proxyResult);
    }

    public static PaymentCallbackResult createCallbackResultFailure(byte[] callbackResponse, Failure failure) {
        return new PaymentCallbackResult().setResponse(callbackResponse).setResult(createCallbackProxyResultFailure(failure));
    }

    public static PaymentCallbackResult createCallbackResultFailure(Failure failure) {
        return createCallbackResultFailure(DEFAULT_ERROR_CODE.getBytes(), failure);
    }

    // RecurrentTokenCallbackResult
    public static RecurrentTokenCallbackResult createRecurrentTokenCallbackResult(byte[] callbackResponse, RecurrentTokenProxyResult proxyResult) {
        return new RecurrentTokenCallbackResult().setResponse(callbackResponse).setResult(proxyResult);
    }

    public static RecurrentTokenCallbackResult createRecurrentTokenCallbackResultFailure(byte[] callbackResponse, Failure failure) {
        return new RecurrentTokenCallbackResult().setResponse(callbackResponse).setResult(createRecurrentTokenProxyResult(createRecurrentTokenFinishIntentFailure(failure)));
    }

    public static RecurrentTokenCallbackResult createRecurrentTokenCallbackResultFailure(Failure failure) {
        return createRecurrentTokenCallbackResultFailure(DEFAULT_ERROR_CODE.getBytes(), failure);
    }

    // FinishIntent
    public static Intent createFinishIntentSuccess() {
        return Intent.finish(new FinishIntent(createFinishStatusSuccess()));
    }

    public static Intent createFinishIntentSuccessWithToken(String token) {
        return Intent.finish(new FinishIntent(createFinishStatusSuccess(token)));
    }

    public static Intent createFinishIntentFailure(String code, String description) {
        return Intent.finish(new FinishIntent(createFinishStatusFailure(createFailure(code, description))));
    }

    public static Intent createFinishIntentFailure(Failure failure) {
        return Intent.finish(new FinishIntent(createFinishStatusFailure(failure)));
    }

    public static Intent createIntentWithSuspendIntent(String tag, Integer timer, UserInteraction userInteraction) {
        return Intent.suspend(createSuspendIntent(tag, timer, userInteraction));
    }

    public static Intent createIntentWithSuspendIntent(String tag, Integer timer) {
        return createIntentWithSuspendIntent(tag, timer, null);
    }

    public static SuspendIntent createSuspendIntent(String tag, Integer timer, UserInteraction userInteraction) {
        return new SuspendIntent(tag, createTimerTimeout(timer)).setUserInteraction(userInteraction);
    }

    public static Intent createIntentWithSleepIntent(Integer timer) {
        return Intent.sleep(createSleepIntent(createTimerTimeout(timer)));
    }

    public static Intent createIntentWithSleepIntent(Integer timer, UserInteraction userInteraction) {
        return Intent.sleep(createSleepIntent(timer, userInteraction));
    }

    public static SleepIntent createSleepIntent(Timer timer) {
        return new SleepIntent(timer);
    }

    public static SleepIntent createSleepIntent(Integer timer, UserInteraction userInteraction) {
        return createSleepIntent(createTimerTimeout(timer)).setUserInteraction(userInteraction);
    }

    public static FinishStatus createFinishStatusFailure(Failure failure) {
        return FinishStatus.failure(failure);
    }

    public static FinishStatus createFinishStatusSuccess() {
        return FinishStatus.success(new Success());
    }

    public static FinishStatus createFinishStatusSuccess(String token) {
        return FinishStatus.success(new Success().setToken(token));
    }

    public static UserInteraction createPostUserInteraction(String url, Map<String, String> form) {
        return createUserInteraction(createBrowserPostRequest(url, form));
    }

    public static UserInteraction createGetUserInteraction(String url) {
        return createUserInteraction(createBrowserGetRequest(url));
    }

    public static UserInteraction createUserInteraction(BrowserHTTPRequest browserHTTPRequest) {
        return UserInteraction.redirect(browserHTTPRequest);
    }

    public static BrowserHTTPRequest createBrowserPostRequest(String url, Map<String, String> form) {
        return BrowserHTTPRequest.post_request(new BrowserPostRequest(url, form));
    }

    public static BrowserHTTPRequest createBrowserGetRequest(String url) {
        return BrowserHTTPRequest.get_request(new BrowserGetRequest(url));
    }

    public static BankCard createBankCardWithToken(BankCard bankCard, BankCardTokenProvider bankCardTokenProvider) {
        bankCard.setTokenProvider(bankCardTokenProvider);
        return bankCard;
    }

}
