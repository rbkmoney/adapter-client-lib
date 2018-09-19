package com.rbkmoney.cds.client.storage.condition;


import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class CdsClientStorageCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(
            ConditionContext context,
            AnnotatedTypeMetadata annotatedTypeMetadata
    ) {

        if (isUrlEmpty(context.getEnvironment())) {
            return ConditionOutcome.noMatch(
                    "CDS Client Storage is disabled, because 'cds.client.storage.url' is empty.");
        }

        return ConditionOutcome.match();
    }

    private boolean isUrlEmpty(Environment env) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "cds.client.storage.");
        return StringUtils.isEmpty(resolver.getProperty("url", ""));
    }

}

