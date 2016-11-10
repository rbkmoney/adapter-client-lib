package com.rbkmoney.dominant.client.repository.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;

public class DominantClientRepositoryPropertiesTest {

    @Mock
    private Resource resource;

    private DominantClientRepositoryProperties properties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        properties = new DominantClientRepositoryProperties();
    }

    @Test
    public void testSetAndGetUrl() {
        properties.setRepository(resource);
        Assert.assertNotNull(properties.getRepository());
    }
}