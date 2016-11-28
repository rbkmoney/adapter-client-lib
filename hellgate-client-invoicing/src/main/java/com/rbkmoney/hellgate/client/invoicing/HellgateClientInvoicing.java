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
        LOGGER.info("Hellgate Invoicing: create start with userInfo {}, invoiceParams {}",
                userInfo, invoiceParams
        );
        String invoiceId = invoicingSrv.create(userInfo, invoiceParams);
        LOGGER.info("Hellgate Invoicing: create finish with invoiceId {}", invoiceId);
        return invoiceId;
    }

    @Override
    public InvoiceState get(UserInfo userInfo, String invoiceId) throws InvalidUser, UserInvoiceNotFound, TException {
        LOGGER.info("Hellgate Invoicing: get start with userInfo {}, invoiceId {}",
                userInfo, invoiceId
        );
        InvoiceState invoiceState = invoicingSrv.get(userInfo, invoiceId);
        LOGGER.info("Hellgate Invoicing: get finish with invoiceState {}", invoiceState);
        return invoiceState;
    }

    @Override
    public List<Event> getEvents(UserInfo userInfo, String invoiceId, EventRange eventRange) throws InvalidUser, UserInvoiceNotFound, EventNotFound, InvalidRequest, TException {
        LOGGER.info("Hellgate Invoicing: getEvents start with userInfo{}, invoiceId {}, eventRange {}",
                userInfo, invoiceId, eventRange
        );
        List<Event> events = invoicingSrv.getEvents(userInfo, invoiceId, eventRange);
        LOGGER.info("Hellgate Invoicing: getEvents finish");
        return events;
    }

    @Override
    public String startPayment(UserInfo userInfo, String invoiceId, InvoicePaymentParams invoicePaymentParams) throws TException {
        LOGGER.info("Hellgate Invoicing: startPayment start with userInfo {}, invoiceId {}, invoicePaymentParams {}",
                userInfo, invoiceId, invoicePaymentParams
        );
        String paymentId = invoicingSrv.startPayment(userInfo, invoiceId, invoicePaymentParams);
        LOGGER.info("Hellgate Invoicing: startPayment finish with paymentId {}", paymentId);
        return paymentId;
    }

    @Override
    public InvoicePayment getPayment(UserInfo userInfo, String invoiceId, String invoicePaymentId) throws InvalidUser, UserInvoiceNotFound, InvoicePaymentNotFound, TException {
        LOGGER.info("Hellgate Invoicing: get start");
        InvoicePayment invoicePayment = invoicingSrv.getPayment(userInfo, invoiceId, invoicePaymentId);
        LOGGER.info("Hellgate Invoicing: get finish with InvoicePayment {}", invoicePayment);
        return invoicePayment;
    }

    @Override
    public void fulfill(UserInfo userInfo, String invoiceId, String reason) throws InvalidUser, UserInvoiceNotFound, InvalidInvoiceStatus, TException {
        LOGGER.info("Hellgate Invoicing: fulfill start with userInfo {}, invoiceId {}, reason {}",
                userInfo, invoiceId, reason
        );
        invoicingSrv.fulfill(userInfo, invoiceId, reason);
        LOGGER.info("Hellgate Invoicing: fulfill finish");
    }

    @Override
    public void rescind(UserInfo userInfo, String invoiceId, String reason) throws InvalidUser, UserInvoiceNotFound, InvalidInvoiceStatus, InvoicePaymentPending, TException {
        LOGGER.info("Hellgate Invoicing: rescind start with userInfo {}, invoiceId {}, reason {}",
                userInfo, invoiceId, reason
        );
        invoicingSrv.rescind(userInfo, invoiceId, reason);
        LOGGER.info("Hellgate Invoicing: rescind finish");
    }

}
