package com.rbkmoney.hellgate.client.eventsink.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;

public class HellgateClientEventSinkPropertiesTest {

    @Mock
    private Resource resource;

    private HellgateClientEventSinkProperties properties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        properties = new HellgateClientEventSinkProperties();
    }

    @Test
    public void testSetAndGetUrl() {
        properties.setEventSink(resource);
        Assert.assertNotNull(properties.getEventSink());
    }

}