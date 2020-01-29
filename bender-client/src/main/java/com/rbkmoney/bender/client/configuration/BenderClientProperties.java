package com.rbkmoney.bender.client.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "bender")
public class BenderClientProperties {

    @Valid
    private Client client;

    @NotNull
    private String namespace;

    @Getter
    @Setter
    public static class Client {

        @NotNull
        private Resource url;

        @NotNull
        private int networkTimeout = 5000;
    }

}
