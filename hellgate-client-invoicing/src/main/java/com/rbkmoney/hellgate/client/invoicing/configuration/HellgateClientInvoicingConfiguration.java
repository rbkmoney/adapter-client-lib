package com.rbkmoney.hellgate.client.invoicing.configuration;

import com.rbkmoney.damsel.payment_processing.InvoicingSrv;
import com.rbkmoney.hellgate.client.invoicing.condition.HellgateClientInvoicingCondition;
import com.rbkmoney.woody.api.ClientBuilder;
import com.rbkmoney.woody.api.event.ClientEventListener;
import com.rbkmoney.woody.api.event.CompositeClientEventListener;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import com.rbkmoney.woody.thrift.impl.http.event.ClientEventLogListener;
import com.rbkmoney.woody.thrift.impl.http.event.HttpClientEventLogListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({HellgateClientInvoicingProperties.class})
@Conditional(HellgateClientInvoicingCondition.class)
public class HellgateClientInvoicingConfiguration {

    @Autowired
    private HellgateClientInvoicingProperties properties;

    @Bean
    public InvoicingSrv.Iface invoicingSrv() throws IOException {
        return clientBuilderHellgateInvoicing()
                .withEventListener(hellgateInvoicingListener())
                .withAddress(properties.getInvoicing().getURI())
                .build(InvoicingSrv.Iface.class);
    }

    @Bean
    public ClientEventListener hellgateInvoicingListener() {
        return new CompositeClientEventListener(
                new ClientEventLogListener(),
                new HttpClientEventLogListener()
        );
    }

    @Bean
    public ClientBuilder clientBuilderHellgateInvoicing() {
        return new THSpawnClientBuilder();
    }
}
