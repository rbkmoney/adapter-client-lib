package com.rbkmoney.hellgate.client.eventsink;

import com.rbkmoney.damsel.payment_processing.Event;
import com.rbkmoney.damsel.payment_processing.EventRange;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HellgateClientEventSinkTest {

    @Mock
    HellgateClientEventSink client;

    @Mock
    EventRange eventRange;

    @Before
    public void setUp() {
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under test.
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEvents() throws Exception {
        List<Event> events = Collections.EMPTY_LIST;

        Mockito.when(client.getEvents(eventRange)).thenReturn(events);

        assertEquals(events, client.getEvents(eventRange));
    }

    @Test
    public void testGetLastEventID () throws Exception {
        long eventId = 0L;

        Mockito.when(client.getLastEventID()).thenReturn(eventId);

        assertEquals(eventId, client.getLastEventID());
    }

}
