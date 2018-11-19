package com.rbkmoney.adapter.helpers.hellgate.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "hellgate.client.adapter")
@Validated
@Getter
@Setter
public class HellgateAdapterClientProperties {

    @NotNull
    private Resource url;

    @NotNull
    private int networkTimeout = 5000;
}
