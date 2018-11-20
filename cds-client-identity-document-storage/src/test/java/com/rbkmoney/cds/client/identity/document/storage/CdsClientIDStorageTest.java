package com.rbkmoney.cds.client.identity.document.storage;

import com.rbkmoney.damsel.identity_document_storage.IdentityDocument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class CdsClientIDStorageTest {

    @Mock
    private IdentityDocument identityDocument;

    @Mock
    private CdsClientIDStorage client;


    @Before
    public void setUp() throws Exception {
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under test.
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void put() {
        String response = "response";
        Mockito.when(client.put(identityDocument)).thenReturn(response);

        assertEquals(response, client.put(identityDocument));
    }

    @Test
    public void get() {
        String token = "token";
        Mockito.when(client.get(token)).thenReturn(identityDocument);

        assertEquals(identityDocument, client.get(token));
    }
}