package com.rbkmoney.hellgate.client.invoicing;

import com.rbkmoney.damsel.base.InvalidRequest;
import com.rbkmoney.damsel.domain.InvoicePayment;
import com.rbkmoney.damsel.payment_processing.*;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HellgateClientInvoicing implements InvoicingSrv.Iface {

    private final static Logger LOGGER = LoggerFactory.getLogger(HellgateClientInvoicing.class);

    @Autowired
    private InvoicingSrv.Iface invoicingSrv;

    @Override
    public String create(UserInfo userInfo, InvoiceParams invoiceParams) throws TException {
        LOGGER.info("Hellgate Invoicing: create start");
        String response = invoicingSrv.create(userInfo, invoiceParams);
        LOGGER.info("Hellgate Invoicing: create finish");
        return response;
    }

    @Override
    public InvoiceState get(UserInfo userInfo, String invoiceId) throws InvalidUser, UserInvoiceNotFound, TException {
        LOGGER.info("Hellgate Invoicing: get start");
        InvoiceState response = invoicingSrv.get(userInfo, invoiceId);
        LOGGER.info("Hellgate Invoicing: get finish");
        return response;
    }

    @Override
    public List<Event> getEvents(UserInfo userInfo, String invoiceId, EventRange eventRange) throws InvalidUser, UserInvoiceNotFound, EventNotFound, InvalidRequest, TException {
        LOGGER.info("Hellgate Invoicing: getEvents start");
        List<Event> response = invoicingSrv.getEvents(userInfo, invoiceId, eventRange);
        LOGGER.info("Hellgate Invoicing: getEvents finish");
        return response;
    }

    @Override
    public String startPayment(UserInfo userInfo, String invoiceId, InvoicePaymentParams invoicePaymentParams) throws TException {
        LOGGER.info("Hellgate Invoicing: startPayment start");
        String response = invoicingSrv.startPayment(userInfo, invoiceId, invoicePaymentParams);
        LOGGER.info("Hellgate Invoicing: startPayment finish");
        return response;
    }

    @Override
    public InvoicePayment getPayment(UserInfo userInfo, String invoiceId, String invoicePaymentId) throws InvalidUser, UserInvoiceNotFound, InvoicePaymentNotFound, TException {
        LOGGER.info("Hellgate Invoicing: get start");
        InvoicePayment response = invoicingSrv.getPayment(userInfo, invoiceId, invoicePaymentId);
        LOGGER.info("Hellgate Invoicing: get finish");
        return response;
    }

    @Override
    public void fulfill(UserInfo userInfo, String invoiceId, String reason) throws InvalidUser, UserInvoiceNotFound, InvalidInvoiceStatus, TException {
        LOGGER.info("Hellgate Invoicing: fulfill start");
        invoicingSrv.fulfill(userInfo, invoiceId, reason);
        LOGGER.info("Hellgate Invoicing: fulfill finish");
    }

    @Override
    public void rescind(UserInfo userInfo, String invoiceId, String reason) throws InvalidUser, UserInvoiceNotFound, InvalidInvoiceStatus, InvoicePaymentPending, TException {
        LOGGER.info("Hellgate Invoicing: rescind start");
        invoicingSrv.rescind(userInfo, invoiceId, reason);
        LOGGER.info("Hellgate Invoicing: rescind finish");
    }

}
