package com.rbkmoney.adapter.helpers.hellgate.configuration;

import com.rbkmoney.damsel.proxy_provider.ProviderProxyHostSrv;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({HellgateClientProperties.class})
public class HellgateClientConfiguration {

    @Bean
    public ProviderProxyHostSrv.Iface providerProxySrv(HellgateClientProperties properties) throws IOException {
        return new THSpawnClientBuilder()
                .withAddress(properties.getUrl().getURI())
                .withNetworkTimeout(properties.getTimeout())
                .build(ProviderProxyHostSrv.Iface.class);
    }

}
