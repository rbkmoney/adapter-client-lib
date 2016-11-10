package com.rbkmoney.hellgate.client.party.management.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;

public class HellgateClientPartyManagementPropertiesTest {

    @Mock
    private Resource resource;

    private HellgateClientPartyManagementProperties properties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        properties = new HellgateClientPartyManagementProperties();
    }

    @Test
    public void testSetAndGetUrl() {
        properties.setPartyManagement(resource);
        Assert.assertNotNull(properties.getPartyManagement());
    }

}