package com.rbkmoney.dominant.repository.configuration;

import com.rbkmoney.damsel.domain_config.RepositoryClientSrv;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({DominantRepositoryClientProperties.class})
public class DominantRepositoryClientConfiguration {

    @Bean
    public RepositoryClientSrv.Iface repositoryClientSrv(DominantRepositoryClientProperties properties) throws IOException {
        return new THSpawnClientBuilder()
                .withAddress(properties.getUrl().getURI())
                .withNetworkTimeout(properties.getNetworkTimeout())
                .build(RepositoryClientSrv.Iface.class);
    }

}
