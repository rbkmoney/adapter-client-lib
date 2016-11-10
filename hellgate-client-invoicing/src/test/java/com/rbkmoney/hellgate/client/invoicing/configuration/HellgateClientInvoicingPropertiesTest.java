package com.rbkmoney.hellgate.client.invoicing.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;


public class HellgateClientInvoicingPropertiesTest {

    @Mock
    private Resource resource;

    private HellgateClientInvoicingProperties properties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        properties = new HellgateClientInvoicingProperties();
    }

    @Test
    public void testSetAndGetUrl() {
        properties.setInvoicing(resource);
        Assert.assertNotNull(properties.getInvoicing());
    }
}