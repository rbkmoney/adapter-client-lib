package com.rbkmoney.hellgate.client.proxy.host.provider.configuration;

import com.rbkmoney.damsel.proxy_provider.ProviderProxySrv;
import com.rbkmoney.hellgate.client.proxy.host.provider.condition.HellgateClientProxyHostProviderCondition;
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
@EnableConfigurationProperties({HellgateClientProxyHostProviderProperties.class})
@Conditional(HellgateClientProxyHostProviderCondition.class)
public class HellgateClientProxyHostProviderConfiguration {

    @Autowired
    private HellgateClientProxyHostProviderProperties properties;

    @Bean
    public ProviderProxySrv.Iface providerProxySrv() throws IOException {
        return clientBuilderHellgateProviderProxy()
                .withEventListener(hellgateProviderProxyListener())
                .withAddress(properties.getProxyHostProvider().getURI())
                .build(ProviderProxySrv.Iface.class);
    }

    @Bean
    public ClientEventListener hellgateProviderProxyListener() {
        return new CompositeClientEventListener(
                new ClientEventLogListener(),
                new HttpClientEventLogListener()
        );
    }

    public ClientBuilder clientBuilderHellgateProviderProxy() {
        return new THSpawnClientBuilder();
    }

}
