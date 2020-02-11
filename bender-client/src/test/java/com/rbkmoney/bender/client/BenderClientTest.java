package com.rbkmoney.bender.client;

import com.rbkmoney.bender.*;
import com.rbkmoney.bender.client.configuration.BenderClientProperties;
import com.rbkmoney.msgpack.Nil;
import com.rbkmoney.msgpack.Value;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class BenderClientTest {

    private static final String INTERNAL_ID = "InternalId";

    @Mock
    private BenderSrv.Iface benderSrv;

    @Mock
    private BenderClientProperties properties;

    private BenderClient client;

    @Before
    public void setUp() {
        initMocks(BenderClientTest.class);
        client = new BenderClient(benderSrv, properties);
    }

    @Test
    public void getInternalIdTest() throws TException {
        GetInternalIDResult result = new GetInternalIDResult();
        result.setInternalId(INTERNAL_ID);
        Mockito.when(benderSrv.getInternalID(anyString())).thenReturn(result);

        assertEquals(INTERNAL_ID, client.getInternalID(INTERNAL_ID).getInternalId());
        verify(benderSrv, times(1)).getInternalID(INTERNAL_ID);
    }

    @Test
    public void generateIdTest() throws TException {
        GenerationResult result = new GenerationResult();
        result.setInternalId(INTERNAL_ID);
        Mockito.when(benderSrv.generateID(anyString(), any(), any())).thenReturn(result);

        GenerationSchema generationSchema = GenerationSchema.snowflake(new SnowflakeSchema());
        Value value = Value.nl(new Nil());
        assertEquals(INTERNAL_ID, client.generateId(INTERNAL_ID, generationSchema, value).getInternalId());
        verify(benderSrv, times(1)).generateID(INTERNAL_ID, generationSchema, value);
    }

}
