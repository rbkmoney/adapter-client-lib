package com.rbkmoney.dominant.client.repository.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;


@ConfigurationProperties(prefix = "rbkmoney.dominant.client.url")
public class DominantClientRepositoryProperties {

    private Resource repository;

    public Resource getRepository() {
        return repository;
    }

    public void setRepository(Resource repository) {
        this.repository = repository;
    }

}
