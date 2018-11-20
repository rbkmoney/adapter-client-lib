package com.rbkmoney.cds.client.keyring;

import com.rbkmoney.damsel.cds.KeyringSrv;
import com.rbkmoney.damsel.cds.UnlockStatus;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.nio.ByteBuffer;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class CdsClientKeyringTest {

    private CdsClientKeyring client;

    @Mock
    private UnlockStatus unlockStatus;

    @Mock
    private List<ByteBuffer> list;

    @Mock
    private KeyringSrv.Iface keyringSrv;


    @Before
    public void setUp() throws Exception {
        initMocks(CdsClientKeyringTest.class);
        client = new CdsClientKeyring(keyringSrv);
    }

    @Test
    public void unlock() throws TException {
        ByteBuffer keyShare = ByteBuffer.wrap("keyShare".getBytes());

        Mockito.when(client.unlock(keyShare)).thenReturn(unlockStatus);

        assertEquals(unlockStatus, client.unlock(keyShare));
        verify(keyringSrv, times(1)).unlock(keyShare);
    }

    @Test
    public void init() throws TException {
        short treshold = 1;
        short numShares = 2;
        Mockito.when(client.init(treshold, numShares)).thenReturn(list);

        assertEquals(list, client.init(treshold, numShares));
        verify(keyringSrv, times(1)).init(treshold, numShares);
    }

}