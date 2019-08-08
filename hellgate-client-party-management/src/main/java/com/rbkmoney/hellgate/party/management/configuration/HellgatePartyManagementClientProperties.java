package com.rbkmoney.hellgate.party.management.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "hellgate.client.party.management")
public class HellgatePartyManagementClientProperties {

    @NotNull
    private Resource url;

    @NotNull
    private int networkTimeout = 5000;

}

