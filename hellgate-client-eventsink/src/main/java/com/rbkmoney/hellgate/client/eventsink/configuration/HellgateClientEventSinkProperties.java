package com.rbkmoney.hellgate.client.eventsink.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "rbkmoney.hellgate.client.url")
public class HellgateClientEventSinkProperties {

    private Resource eventSink;

    public Resource getEventSink() {
        return eventSink;
    }

    public void setEventSink(Resource eventSink) {
        this.eventSink = eventSink;
    }

}
