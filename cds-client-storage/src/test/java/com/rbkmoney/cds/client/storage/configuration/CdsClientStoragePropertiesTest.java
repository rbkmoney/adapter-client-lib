package com.rbkmoney.cds.client.storage.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;

public class CdsClientStoragePropertiesTest {

    @Mock
    private Resource resource;

    private CdsClientStorageProperties properties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        properties = new CdsClientStorageProperties();
    }

    @Test
    public void testSetAndGetUrl() {
        properties.setUrl(resource);
        Assert.assertNotNull(properties.getUrl());
    }
}