package com.rbkmoney.hellgate.client.proxy.host.provider.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;


public class HellgateClientProxyHostProviderCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
                                            AnnotatedTypeMetadata annotatedTypeMetadata) {

        if (isUrlProxyHostProviderEmpty(context.getEnvironment())) {
            return ConditionOutcome.noMatch(
                    "Hellgate Client Proxy Host Provider is disabled, because 'rbkmoney.hellgate.client.url.proxy_host_provider' is empty.");
        }

        return ConditionOutcome.match();
    }

    private boolean isUrlProxyHostProviderEmpty(Environment env) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "rbkmoney.hellgate.client.url.");
        return StringUtils.isEmpty(resolver.getProperty("proxy_host_provider", ""));
    }

}
