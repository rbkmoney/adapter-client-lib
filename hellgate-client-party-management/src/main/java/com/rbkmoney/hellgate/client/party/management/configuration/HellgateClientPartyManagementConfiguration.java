package com.rbkmoney.hellgate.client.party.management.configuration;

import com.rbkmoney.damsel.payment_processing.PartyManagementSrv;
import com.rbkmoney.hellgate.client.party.management.condition.HellgateClientPartyManagementCondition;
import com.rbkmoney.woody.api.ClientBuilder;
import com.rbkmoney.woody.api.event.ClientEventListener;
import com.rbkmoney.woody.api.event.CompositeClientEventListener;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import com.rbkmoney.woody.thrift.impl.http.event.ClientEventLogListener;
import com.rbkmoney.woody.thrift.impl.http.event.HttpClientEventLogListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({HellgateClientPartyManagementProperties.class})
@Conditional(HellgateClientPartyManagementCondition.class)
public class HellgateClientPartyManagementConfiguration {

    @Autowired
    private HellgateClientPartyManagementProperties properties;

    @Bean
    public PartyManagementSrv.Iface partyManagementSrv(ClientEventListener listenerSrv) throws IOException {
        return clientBuilderHellgatePartyManagement()
                .withEventListener(listenerSrv)
                .withAddress(properties.getPartyManagement().getURI())
                .build(PartyManagementSrv.Iface.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public ClientEventListener listenerSrv() {
        return new CompositeClientEventListener(
                new ClientEventLogListener(),
                new HttpClientEventLogListener()
        );
    }

    @Bean
    public ClientBuilder clientBuilderHellgatePartyManagement() {
        return new THSpawnClientBuilder();
    }

}
