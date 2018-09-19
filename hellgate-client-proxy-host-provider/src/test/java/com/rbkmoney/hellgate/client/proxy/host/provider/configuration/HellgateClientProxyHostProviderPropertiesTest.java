package com.rbkmoney.hellgate.client.proxy.host.provider.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;

public class HellgateClientProxyHostProviderPropertiesTest {

    @Mock
    private Resource resource;

    private HellgateClientProxyHostProviderProperties properties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        properties = new HellgateClientProxyHostProviderProperties();
    }

    @Test
    public void testSetAndGetUrl() {
        properties.setUrl(resource);
        Assert.assertNotNull(properties.getUrl());
    }

}