package com.rbkmoney.hellgate.client.invoicing.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "rbkmoney.hellgate.client.url")
public class HellgateClientInvoicingProperties {

    private Resource invoicing;

    public Resource getInvoicing() {
        return invoicing;
    }

    public void setInvoicing(Resource invoicing) {
        this.invoicing = invoicing;
    }
}
