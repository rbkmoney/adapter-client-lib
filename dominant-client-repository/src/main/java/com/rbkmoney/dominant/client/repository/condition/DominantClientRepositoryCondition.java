package com.rbkmoney.dominant.client.repository.condition;


import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class DominantClientRepositoryCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
                                            AnnotatedTypeMetadata annotatedTypeMetadata) {

        if (isUrlRepositoryEmpty(context.getEnvironment())) {
            return ConditionOutcome.noMatch(
                    "Dominant Client Repository is disabled, because 'rbkmoney.dominant.client.url.repository' is empty.");
        }

        return ConditionOutcome.match();
    }

    private boolean isUrlRepositoryEmpty(Environment env) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "rbkmoney.dominant.client.url.");
        return StringUtils.isEmpty(resolver.getProperty("repository", ""));
    }

}
