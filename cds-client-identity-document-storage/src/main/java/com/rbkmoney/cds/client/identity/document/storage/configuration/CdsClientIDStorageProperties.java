package com.rbkmoney.cds.client.identity.document.storage.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "cds.client.identity-document-storage")
@Validated
@Getter
@Setter
public class CdsClientIDStorageProperties {

    @NotNull
    private Resource url;

    @NotNull
    private int networkTimeout = 5000;

}
