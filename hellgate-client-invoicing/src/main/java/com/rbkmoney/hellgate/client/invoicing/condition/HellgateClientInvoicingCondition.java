package com.rbkmoney.hellgate.client.invoicing.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class HellgateClientInvoicingCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
                                            AnnotatedTypeMetadata annotatedTypeMetadata) {

        if (isUrlInvoicingEmpty(context.getEnvironment())) {
            return ConditionOutcome.noMatch(
                    "Hellgate Client Invoicing is disabled, because 'rbkmoney.hellgate.client.url.invoicing' is empty.");
        }

        return ConditionOutcome.match();
    }

    private boolean isUrlInvoicingEmpty(Environment env) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "rbkmoney.hellgate.client.url.");
        return StringUtils.isEmpty(resolver.getProperty("invoicing", ""));
    }
}
