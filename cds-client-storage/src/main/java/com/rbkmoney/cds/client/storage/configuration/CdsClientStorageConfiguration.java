package com.rbkmoney.cds.client.storage.configuration;

import com.rbkmoney.damsel.cds.StorageSrv;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({CdsClientStorageProperties.class})
public class CdsClientStorageConfiguration {

    @Bean
    public StorageSrv.Iface storageSrv(CdsClientStorageProperties properties) throws IOException {
        return new THSpawnClientBuilder()
                .withAddress(properties.getUrl().getURI())
                .withNetworkTimeout(properties.getNetworkTimeout())
                .build(StorageSrv.Iface.class);
    }

}
