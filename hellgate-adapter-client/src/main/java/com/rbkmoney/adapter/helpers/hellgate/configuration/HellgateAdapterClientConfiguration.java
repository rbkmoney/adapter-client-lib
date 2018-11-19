package com.rbkmoney.adapter.helpers.hellgate.configuration;

import com.rbkmoney.damsel.proxy_provider.ProviderProxyHostSrv;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({HellgateAdapterClientProperties.class})
public class HellgateAdapterClientConfiguration {

    @Bean
    public ProviderProxyHostSrv.Iface providerProxySrv(HellgateAdapterClientProperties properties) throws IOException {
        return new THSpawnClientBuilder()
                .withAddress(properties.getUrl().getURI())
                .withNetworkTimeout(properties.getTimeout())
                .build(ProviderProxyHostSrv.Iface.class);
    }

}
