package com.rbkmoney.cds.client.identity.document.storage.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;

public class CdsClientIdendtityDocumentStoragePropertiesTest {

    @Mock
    private Resource resource;

    private CdsClientIDStorageProperties properties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        properties = new CdsClientIDStorageProperties();
    }

    @Test
    public void testSetAndGetUrl() {
        properties.setUrl(resource);
        Assert.assertNotNull(properties.getUrl());
    }

}