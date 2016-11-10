package com.rbkmoney.cds.client.storage.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "rbkmoney.cds.client.url")
public class CdsClientStorageProperties {

    private Resource storage;

    public Resource getStorage() {
        return storage;
    }

    public void setStorage(Resource storage) {
        this.storage = storage;
    }

}
