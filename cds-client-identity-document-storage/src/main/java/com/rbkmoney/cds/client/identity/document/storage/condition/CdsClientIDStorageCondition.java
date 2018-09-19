package com.rbkmoney.cds.client.identity.document.storage.condition;


import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class CdsClientIDStorageCondition extends SpringBootCondition {

    public static final String DEFAULT_TIMEOUT = "5000";


    @Override
    public ConditionOutcome getMatchOutcome(
            ConditionContext context,
            AnnotatedTypeMetadata annotatedTypeMetadata
    ) {

        if (isUrlEmpty(context.getEnvironment())) {
            return ConditionOutcome.noMatch(
                    "CDS Client Keyring is disabled, because 'cds.client.identity-document-storage.url' is empty.");
        }

        return ConditionOutcome.match();
    }

    private boolean isUrlEmpty(Environment env) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "cds.client.identity-document-storage.");
        return StringUtils.isEmpty(resolver.getProperty("url", ""));
    }

    private boolean isTimeoutEmpty(Environment env) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "cds.client.identity-document-storage.");
        return StringUtils.isEmpty(resolver.getProperty("timeout", DEFAULT_TIMEOUT));
    }

}
