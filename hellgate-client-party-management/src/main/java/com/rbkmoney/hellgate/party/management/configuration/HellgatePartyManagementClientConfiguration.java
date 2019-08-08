package com.rbkmoney.hellgate.party.management.configuration;

import com.rbkmoney.damsel.payment_processing.PartyManagementSrv;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({HellgatePartyManagementClientProperties.class})
public class HellgatePartyManagementClientConfiguration {

    @Bean
    public PartyManagementSrv.Iface partyManagementSrv(HellgatePartyManagementClientProperties properties) throws IOException {
        return new THSpawnClientBuilder()
                .withAddress(properties.getUrl().getURI())
                .withNetworkTimeout(properties.getNetworkTimeout())
                .build(PartyManagementSrv.Iface.class);
    }

}
