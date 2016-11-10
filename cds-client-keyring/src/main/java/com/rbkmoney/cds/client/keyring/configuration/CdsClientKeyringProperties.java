package com.rbkmoney.cds.client.keyring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "rbkmoney.cds.client.url")
public class CdsClientKeyringProperties {

    private Resource keyring;

    public Resource getKeyring() {
        return keyring;
    }

    public void setKeyring(Resource keyring) {
        this.keyring = keyring;
    }

}
