package com.rbkmoney.hellgate.client.proxy.host.provider.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;


public class HellgateClientProxyHostProviderCondition extends SpringBootCondition {

    public static final String DEFAULT_TIMEOUT = "5000";

    @Override
    public ConditionOutcome getMatchOutcome(
            ConditionContext context,
            AnnotatedTypeMetadata annotatedTypeMetadata
    ) {

        if (isUrlProxyHostProviderEmpty(context.getEnvironment())) {
            return ConditionOutcome.noMatch(
                    "Hellgate Client Proxy Host Provider is disabled, because 'hellgate.client.proxy-host-provider.url' is empty.");
        }

        return ConditionOutcome.match();
    }

    private boolean isUrlProxyHostProviderEmpty(Environment env) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "hellgate.client.proxy-host-provider.");
        return StringUtils.isEmpty(resolver.getProperty("url", ""));
    }

    private boolean isTimeoutEmpty(Environment env) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "hellgate.client.proxy-host-provider.");
        return StringUtils.isEmpty(resolver.getProperty("timeout", DEFAULT_TIMEOUT));
    }

}
