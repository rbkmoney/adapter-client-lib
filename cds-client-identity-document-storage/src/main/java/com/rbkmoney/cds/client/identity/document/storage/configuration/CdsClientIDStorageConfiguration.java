package com.rbkmoney.cds.client.identity.document.storage.configuration;

import com.rbkmoney.cds.client.identity.document.storage.condition.CdsClientIDStorageCondition;
import com.rbkmoney.damsel.identity_document_storage.IdentityDocumentStorageSrv;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(CdsClientIDStorageProperties.class)
@Conditional(CdsClientIDStorageCondition.class)
public class CdsClientIDStorageConfiguration {

    @Bean
    public IdentityDocumentStorageSrv.Iface identityDocumentStorageSrv(CdsClientIDStorageProperties properties) throws IOException {
        return new THSpawnClientBuilder()
                .withAddress(properties.getUrl().getURI())
                .withNetworkTimeout(properties.getTimeout())
                .build(IdentityDocumentStorageSrv.Iface.class);
    }

}
