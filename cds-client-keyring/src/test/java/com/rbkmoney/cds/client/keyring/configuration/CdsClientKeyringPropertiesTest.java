package com.rbkmoney.cds.client.keyring.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;

public class CdsClientKeyringPropertiesTest {

    @Mock
    private Resource resource;

    private CdsClientKeyringProperties properties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        properties = new CdsClientKeyringProperties();
    }

    @Test
    public void testSetAndGetUrl() {
        properties.setUrl(resource);
        Assert.assertNotNull(properties.getUrl());
    }
}