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

    public static com.rbkmoney.damsel.proxy_provider.Cash createCash(Long amount, String name, Integer numericCode, String symbolicCode, Integer exponent) {
        return new com.rbkmoney.damsel.proxy_provider.Cash(amount, createCurrency(name, numericCode, symbolicCode, exponent));
    }

    public static com.rbkmoney.damsel.domain.Cash createCash(CurrencyRef currency, Long amount) {
        return new com.rbkmoney.damsel.domain.Cash(amount, currency);
    }

    public static BankCard createBankCardWithExpDate(String bin, String maskedPan, String token, BankCardPaymentSystem bankCardPaymentSystem, String month, String year, String cardholderName) {
        return createBankCard(bin, maskedPan, token, bankCardPaymentSystem, createBankCardExpDate(month, year), cardholderName);
    }

    public static BankCard createBankCard(String bin, String maskedPan, String token, BankCardPaymentSystem bankCardPaymentSystem, BankCardExpDate bankCardExpDate, String cardholderName) {
        return new BankCard().setBin(bin).setMaskedPan(maskedPan).setToken(token).setPaymentSystem(bankCardPaymentSystem).setExpDate(bankCardExpDate).setCardholderName(cardholderName);
    }

    public static BankCardExpDate createBankCardExpDate(byte month, short year) {
        return new BankCardExpDate(month, year);
    }

    public static BankCardExpDate createBankCardExpDate(String month, String year) {
        return createBankCardExpDate(Byte.parseByte(month), Short.parseShort(year));
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

    public static Currency createCurrency(String name, Integer numericCode, String symbolicCode, Integer exponent) {
        return createCurrency(name, numericCode.shortValue(), symbolicCode, exponent.shortValue());
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
        return new TransactionInfo().setId(paymentId).setExtra(extra).setTimestamp(timestamp);
    }

    public static TransactionInfo createTransactionInfo(String paymentId, Map<String, String> extra) {
        return createTransactionInfo(paymentId, extra, null);
    }

}
