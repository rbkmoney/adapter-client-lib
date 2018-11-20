package com.rbkmoney.cds.client.identity.document.storage;

import com.rbkmoney.damsel.identity_document_storage.IdentityDocument;
import com.rbkmoney.damsel.identity_document_storage.IdentityDocumentStorageSrv;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class CdsClientIDStorageTest {

    @Mock
    private IdentityDocument identityDocument;

    @Mock
    private IdentityDocumentStorageSrv.Iface identityDocumentStorageSrv;

    private CdsClientIDStorage client;


    @Before
    public void setUp() throws Exception {
        initMocks(CdsClientIDStorageTest.class);
        client = new CdsClientIDStorage(identityDocumentStorageSrv);
    }

    @Test
    public void get() throws TException {
        String token = "token";
        Mockito.when(identityDocumentStorageSrv.get(token)).thenReturn(identityDocument);

        assertEquals(identityDocument, client.get(token));
        verify(identityDocumentStorageSrv, times(1)).get(token);
    }
}