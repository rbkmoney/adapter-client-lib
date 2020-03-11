package com.rbkmoney.fistful.client.configuration;

import com.rbkmoney.damsel.p2p_adapter.P2PAdapterHostSrv;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({FistfulClientProperties.class})
public class FistfulConfiguration {

    @Bean
    public P2PAdapterHostSrv.Iface p2PAdapterHostSrv(FistfulClientProperties properties) throws IOException {
        return new THSpawnClientBuilder()
                .withAddress(properties.getUrl().getURI())
                .withNetworkTimeout(properties.getNetworkTimeout())
                .build(P2PAdapterHostSrv.Iface.class);
    }

}
