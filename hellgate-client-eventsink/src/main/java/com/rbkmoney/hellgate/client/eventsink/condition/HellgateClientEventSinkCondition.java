package com.rbkmoney.hellgate.client.eventsink.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class HellgateClientEventSinkCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
                                            AnnotatedTypeMetadata annotatedTypeMetadata) {

        if (isUrlEventSinkEmpty(context.getEnvironment())) {
            return ConditionOutcome.noMatch(
                    "Hellgate Client EventSink is disabled, because 'rbkmoney.hellgate.client.url.eventsink' is empty.");
        }

        return ConditionOutcome.match();
    }

    private boolean isUrlEventSinkEmpty(Environment env) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "rbkmoney.hellgate.client.url.");
        return StringUtils.isEmpty(resolver.getProperty("eventsink", ""));
    }

}
