package com.rbkmoney.hellgate.client.proxy.host.provider.configuration;

import com.rbkmoney.damsel.proxy_provider.ProviderProxyHostSrv;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({HellgateClientProxyHostProviderProperties.class})
public class HellgateClientProxyHostProviderConfiguration {

    @Bean
    public ProviderProxyHostSrv.Iface providerProxySrv(HellgateClientProxyHostProviderProperties properties) throws IOException {
        return new THSpawnClientBuilder()
                .withAddress(properties.getUrl().getURI())
                .withNetworkTimeout(properties.getTimeout())
                .build(ProviderProxyHostSrv.Iface.class);
    }

}
