package com.rbkmoney.cds.client.keyring.configuration;

import com.rbkmoney.cds.client.keyring.condition.CdsClientKeyringCondition;
import com.rbkmoney.damsel.cds.KeyringSrv;
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
@EnableConfigurationProperties({ CdsClientKeyringProperties.class })
@Conditional(CdsClientKeyringCondition.class)
public class CdsClientKeyringConfiguration {

    @Autowired
    CdsClientKeyringProperties properties;

    @Bean
    public KeyringSrv.Iface keyringSrv() throws IOException {
        return clientBuilderKeyring()
                .withEventListener(keyringListener())
                .withAddress(properties.getKeyring().getURI())
                .build(KeyringSrv.Iface.class);
    }

    @Bean
    public ClientEventListener keyringListener() {
        return new CompositeClientEventListener(
                new ClientEventLogListener(),
                new HttpClientEventLogListener()
        );
    }

    @Bean
    public ClientBuilder clientBuilderKeyring() {
        return new THSpawnClientBuilder();
    }
}
