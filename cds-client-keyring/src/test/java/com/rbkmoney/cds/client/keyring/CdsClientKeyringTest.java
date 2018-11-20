package com.rbkmoney.cds.client.keyring;

import com.rbkmoney.damsel.cds.UnlockStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.nio.ByteBuffer;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CdsClientKeyringTest {

    private ByteBuffer keyShare = ByteBuffer.wrap("keyShare".getBytes());
    private short treshold = Short.valueOf("treshold");
    private short numShares = Short.valueOf("num_shares");


    @Mock
    private CdsClientKeyring client;

    @Mock
    private UnlockStatus unlockStatus;

    @Mock
    private List<ByteBuffer> list;

    @Before
    public void setUp() throws Exception {
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under test.
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void unlock() {
        Mockito.when(client.unlock(keyShare)).thenReturn(unlockStatus);

        assertEquals(unlockStatus, client.unlock(keyShare));
    }

    @Test
    public void init() {
        Mockito.when(client.init(treshold, numShares)).thenReturn(list);

        assertEquals(list, client.init(treshold, numShares));
    }

}