package com.rbkmoney.hellgate.client.party.management.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "rbkmoney.hellgate.client.url")
public class HellgateClientPartyManagementProperties {

    private Resource partyManagement;

    public Resource getPartyManagement() {
        return partyManagement;
    }

    public void setPartyManagement(Resource partyManagement) {
        this.partyManagement = partyManagement;
    }
}
