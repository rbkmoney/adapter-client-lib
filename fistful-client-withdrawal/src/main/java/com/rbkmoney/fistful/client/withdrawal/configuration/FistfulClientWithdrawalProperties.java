package com.rbkmoney.fistful.client.withdrawal.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "fistful.client.withdrawal")
public class FistfulClientWithdrawalProperties {

    @NotNull
    private Resource url;

    @NotNull
    private int networkTimeout = 5000;

}
