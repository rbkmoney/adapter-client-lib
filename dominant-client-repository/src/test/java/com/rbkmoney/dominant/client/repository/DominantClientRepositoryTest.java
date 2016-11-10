package com.rbkmoney.dominant.client.repository;

import com.rbkmoney.damsel.domain_config.Commit;
import com.rbkmoney.damsel.domain_config.Reference;
import com.rbkmoney.damsel.domain_config.Snapshot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DominantClientRepositoryTest {

    @Mock
    DominantClientRepository client;

    @Mock
    Commit commit;

    @Mock
    Reference reference;

    @Mock
    Snapshot snapshot;

    private long version = 0L;

    @Before
    public void setUp() {
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under test.
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCommit() throws Exception {

        long result = 1L;

        Mockito.when(client.commit(version, commit)).thenReturn(result);

        assertEquals(result, client.commit(version, commit));
    }

    @Test
    public void testCheckout() throws Exception {
        Mockito.when(client.checkout(reference)).thenReturn(snapshot);

        assertEquals(snapshot, client.checkout(reference));
    }

    @Test
    public void testPull() throws Exception {
        Map<Long, Commit> result = Collections.EMPTY_MAP;

        Mockito.when(client.pull(version)).thenReturn(result);

        assertEquals(result, client.pull(version));
    }

}
