package com.rbkmoney.dominant.client.repository.configuration;

import com.rbkmoney.damsel.domain_config.RepositorySrv;
import com.rbkmoney.dominant.client.repository.condition.DominantClientRepositoryCondition;
import com.rbkmoney.woody.api.ClientBuilder;
import com.rbkmoney.woody.api.event.ClientEventListener;
import com.rbkmoney.woody.api.event.CompositeClientEventListener;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import com.rbkmoney.woody.thrift.impl.http.event.ClientEventLogListener;
import com.rbkmoney.woody.thrift.impl.http.event.HttpClientEventLogListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({DominantClientRepositoryProperties.class})
@Conditional(DominantClientRepositoryCondition.class)
public class DominantClientRepositoryConfiguration {

    @Autowired
    private DominantClientRepositoryProperties properties;

    @Bean
    public RepositorySrv.Iface repositorySrv() throws IOException {
        return clientBuilderRepository()
                .withEventListener(repositoryListener())
                .withAddress(properties.getRepository().getURI())
                .build(RepositorySrv.Iface.class);
    }

    @Bean
    public ClientEventListener repositoryListener() {
        return new CompositeClientEventListener(
                new ClientEventLogListener(),
                new HttpClientEventLogListener()
        );
    }

    @Bean
    public ClientBuilder clientBuilderRepository() {
        return new THSpawnClientBuilder();
    }
}
