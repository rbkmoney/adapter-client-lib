package com.rbkmoney.hellgate.client.proxy.host.provider.condition;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class HellgateClientProxyHostProviderConditionTest {

    private HellgateClientProxyHostProviderCondition condition;
    private AnnotatedTypeMetadata annotatedTypeMetadata;
    private ConditionContext conditionContext;

    @Before
    public void setUp() {
        condition = new HellgateClientProxyHostProviderCondition();
        annotatedTypeMetadata = mock(AnnotatedTypeMetadata.class);
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEmptyUrl() {
        MockEnvironment environment = new MockEnvironment();
        BDDMockito.given(conditionContext.getEnvironment()).willReturn(environment);
        assertFalse(condition.getMatchOutcome(conditionContext, annotatedTypeMetadata).isMatch());
    }

    @Test
    public void testNonEmptyUrl() {
        MockEnvironment environment = new MockEnvironment();
        environment.setProperty("hellgate.client.proxy-host-provider.url", "http://127.0.0.1:8022/v1/proxyhost/provider");
        BDDMockito.given(conditionContext.getEnvironment()).willReturn(environment);
        assertTrue(condition.getMatchOutcome(conditionContext, annotatedTypeMetadata).isMatch());
    }
}