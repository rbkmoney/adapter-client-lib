package com.rbkmoney.hellgate.client.eventsink.configuration;

import com.rbkmoney.damsel.payment_processing.EventSinkSrv;
import com.rbkmoney.hellgate.client.eventsink.condition.HellgateClientEventSinkCondition;
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
@EnableConfigurationProperties({HellgateClientEventSinkProperties.class})
@Conditional(HellgateClientEventSinkCondition.class)
public class HellgateClientEventSinkConfiguration {

    @Autowired
    private HellgateClientEventSinkProperties properties;

    @Bean
    public EventSinkSrv.Iface eventSinkSrv(ClientEventListener listenerSrv) throws IOException {
        return clientBuilderHellgateEventSinkSrv()
                .withEventListener(listenerSrv)
                .withAddress(properties.getEventSink().getURI())
                .build(EventSinkSrv.Iface.class);
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
    public ClientBuilder clientBuilderHellgateEventSinkSrv() {
        return new THSpawnClientBuilder();
    }
}
