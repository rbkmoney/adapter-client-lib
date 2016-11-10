package com.rbkmoney.hellgate.client.invoicing;

import com.rbkmoney.damsel.domain.InvoicePayment;
import com.rbkmoney.damsel.payment_processing.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HellgateClientInvoicingTest {

    @Mock
    HellgateClientInvoicing client;

    @Mock
    UserInfo userInfo;

    @Mock
    InvoiceParams invoiceParams;

    @Mock
    InvoicePaymentParams invoicePaymentParams;

    @Mock
    InvoiceState invoiceState;

    @Mock
    InvoicePayment invoicePayment;

    @Mock
    EventRange eventRange;

    private String invoiceId = "invoiceId";

    private List<Event> events = Collections.EMPTY_LIST;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() throws Exception {
        String response = "";

        Mockito.when(client.create(userInfo, invoiceParams)).thenReturn(response);

        assertEquals(response, client.create(userInfo, invoiceParams));
    }

    @Test
    public void testGet() throws Exception {
        Mockito.when(client.get(userInfo, invoiceId)).thenReturn(invoiceState);

        assertEquals(invoiceState, client.get(userInfo, invoiceId));
    }

    @Test
    public void testGetEvents() throws Exception {
        Mockito.when(client.getEvents(userInfo, invoiceId, eventRange)).thenReturn(events);

        assertEquals(events, client.getEvents(userInfo, invoiceId, eventRange));
    }

    @Test
    public void testStartPayment() throws Exception {
        String paymentId = "paymentId";
        Mockito.when(client.startPayment(userInfo, invoiceId, invoicePaymentParams)).thenReturn(paymentId);

        assertEquals(paymentId, client.startPayment(userInfo, invoiceId, invoicePaymentParams));
    }

    @Test
    public void testGetPayment() throws Exception {
        String invoicePaymentId = "invoicePaymentId";
        Mockito.when(client.getPayment(userInfo, invoiceId, invoicePaymentId)).thenReturn(invoicePayment);

        assertEquals(invoicePayment, client.getPayment(userInfo, invoiceId, invoicePaymentId));
    }

}
