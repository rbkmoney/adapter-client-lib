package com.rbkmoney.hellgate.client.proxy.host.provider.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "rbkmoney.hellgate.client.url")
public class HellgateClientProxyHostProviderProperties {

    private Resource proxyHostProvider;

    public Resource getProxyHostProvider() {
        return proxyHostProvider;
    }

    public void setProxyHostProvider(Resource proxyHostProvider) {
        this.proxyHostProvider = proxyHostProvider;
    }
}
