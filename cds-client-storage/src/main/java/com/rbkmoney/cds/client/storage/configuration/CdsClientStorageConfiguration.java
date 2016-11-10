package com.rbkmoney.cds.client.storage.configuration;

import com.rbkmoney.cds.client.storage.condition.CdsClientStorageCondition;
import com.rbkmoney.damsel.cds.StorageSrv;
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
@EnableConfigurationProperties({ CdsClientStorageProperties.class })
@Conditional(CdsClientStorageCondition.class)
public class CdsClientStorageConfiguration {

    @Autowired
    private CdsClientStorageProperties cdsClientStorageProperties;

    @Bean
    public StorageSrv.Iface storageSrv() throws IOException {
        return clientBuilderStorage()
                .withEventListener(storageListener())
                .withAddress(cdsClientStorageProperties.getStorage().getURI())
                .build(StorageSrv.Iface.class);
    }

    @Bean
    public ClientEventListener storageListener() {
        return new CompositeClientEventListener(
                new ClientEventLogListener(),
                new HttpClientEventLogListener()
        );
    }

    @Bean
    public ClientBuilder clientBuilderStorage() {
        return new THSpawnClientBuilder();
    }
}
