package com.rbkmoney.hellgate.client.proxy.host.provider.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "hellgate.client.proxy-host-provider")
public class HellgateClientProxyHostProviderProperties {

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
