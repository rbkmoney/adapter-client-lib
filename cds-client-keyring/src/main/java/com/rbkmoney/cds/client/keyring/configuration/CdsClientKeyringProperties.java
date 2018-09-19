package com.rbkmoney.cds.client.keyring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "cds.client.keyring")
public class CdsClientKeyringProperties {

    private Resource url;

    private Integer timeout = 5000;


    // ------------------------------------------------------------------------
    // Setters and Getters methods
    // ------------------------------------------------------------------------

    public Resource getUrl() {
        return url;
    }

    public void setUrl(Resource url) {
        this.url = url;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
