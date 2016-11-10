package com.rbkmoney.hellgate.client.eventsink;

import com.rbkmoney.damsel.base.InvalidRequest;
import com.rbkmoney.damsel.payment_processing.*;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HellgateClientEventSink implements EventSinkSrv.Iface {

    private final static Logger LOGGER = LoggerFactory.getLogger(HellgateClientEventSink.class);

    @Autowired
    private EventSinkSrv.Iface eventSink;

    @Override
    public List<Event> getEvents(EventRange eventRange) throws EventNotFound, InvalidRequest, TException {
        LOGGER.info("Hellgate EventSink: getEvents start");
        List<Event> events = eventSink.getEvents(eventRange);
        LOGGER.info("Hellgate EventSink: getEvents finish");
        return events;
    }

    @Override
    public long getLastEventID() throws NoLastEvent, TException {
        LOGGER.info("Hellgate EventSink: getLastEventID start");
        long lastEventID = eventSink.getLastEventID();
        LOGGER.info("Hellgate EventSink: getLastEventID finish");
        return lastEventID;
    }
}
