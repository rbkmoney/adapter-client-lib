package com.rbkmoney.java.damsel.utils.creators;

import com.rbkmoney.damsel.domain.*;

import java.util.Map;

public class DomainPackageCreators {

    public static TargetInvoicePaymentStatus createTargetProcessed() {
        return TargetInvoicePaymentStatus.processed(new InvoicePaymentProcessed());
    }

    public static TargetInvoicePaymentStatus createTargetCaptured() {
        return TargetInvoicePaymentStatus.captured(new InvoicePaymentCaptured());
    }

    public static TargetInvoicePaymentStatus createTargetCancelled() {
        return TargetInvoicePaymentStatus.cancelled(new InvoicePaymentCancelled());
    }

    public static TargetInvoicePaymentStatus createTargetRefunded() {
        return TargetInvoicePaymentStatus.refunded(new InvoicePaymentRefunded());
    }

    public static com.rbkmoney.damsel.proxy_provider.Cash createCash(Currency currency, Long amount) {
        return new com.rbkmoney.damsel.proxy_provider.Cash(amount, currency);
    }

    public static com.rbkmoney.damsel.domain.Cash createCash(CurrencyRef currency, Long amount) {
        return new com.rbkmoney.damsel.domain.Cash(amount, currency);
    }

    public static BankCard createBankCard(String bin, String maskedPan, String token, BankCardPaymentSystem bankCardPaymentSystem) {
        return createBankCard(bin, maskedPan, token, bankCardPaymentSystem);
    }

    public static Shop createShop(CategoryRef categoryRef, ShopDetails shopDetails) {
        return new Shop().setCategory(categoryRef).setDetails(shopDetails);
    }

    public static Invoice createInvoice(String invoiceID, String createdAt, Cash cost) {
        return new Invoice().setId(invoiceID).setCreatedAt(createdAt).setCost(cost);
    }

    public static Currency createCurrency(String name, short numericCode, String symbolicCode, short exponent) {
        return new Currency(name, symbolicCode, numericCode, exponent);
    }

    public static CurrencyRef createCurrencyRef(String symbolicCode) {
        return new CurrencyRef(symbolicCode);
    }

    public static PaymentTool createPaymentTool(BankCard bankCard) {
        return PaymentTool.bank_card(bankCard);
    }

    public static DisposablePaymentResource createDisposablePaymentResource(ClientInfo clientInfo, String paymentSessionId, PaymentTool paymentTool) {
        return new DisposablePaymentResource(paymentTool).setClientInfo(clientInfo).setPaymentSessionId(paymentSessionId);
    }

    public static PaymentResourcePayer createPaymentResourcePayer(ContactInfo contactInfo, DisposablePaymentResource disposablePaymentResource) {
        return new PaymentResourcePayer(disposablePaymentResource, contactInfo);
    }

    public static Payer createPayer(PaymentResourcePayer paymentResourcePayer) {
        return Payer.payment_resource(paymentResourcePayer);
    }

    public static ClientInfo createClientInfo(String fingerprint, String ipAddress) {
        return new ClientInfo().setFingerprint(fingerprint).setIpAddress(ipAddress);
    }

    public static ContactInfo createContactInfo(String email, String phoneNumber) {
        return new ContactInfo().setEmail(email).setPhoneNumber(phoneNumber);
    }

    public static ContactInfo createContactInfoWithEmail(String email) {
        return new ContactInfo().setEmail(email);
    }

    public static ContactInfo createContactInfoWithPhoneNumber(String phoneNumber) {
        return new ContactInfo().setPhoneNumber(phoneNumber);
    }

    public static ShopLocation createShopLocation(String url) {
        return ShopLocation.url(url);
    }

    public static ShopDetails createShopDetails(String name, String description) {
        return new ShopDetails(name).setDescription(description);
    }

    public static Category createCategory(String name, String description) {
        return new Category(name, description);
    }

    public static Failure createFailure(String code, String description) {
        return new Failure(code).setReason(description);
    }

    // TransactionInfo
    public static TransactionInfo createTransactionInfo(String paymentId, Map<String, String> extra, String timestamp) {
        return createTransactionInfo(paymentId, extra, timestamp);
    }

    public static TransactionInfo createTransactionInfo(String paymentId, Map<String, String> extra) {
        return createTransactionInfo(paymentId, extra, null);
    }
    
}
