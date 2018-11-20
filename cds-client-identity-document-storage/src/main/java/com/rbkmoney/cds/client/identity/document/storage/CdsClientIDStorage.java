package com.rbkmoney.cds.client.identity.document.storage;

import com.rbkmoney.cds.client.identity.document.storage.exception.CdsIDStorageException;
import com.rbkmoney.damsel.identity_document_storage.IdentityDocument;
import com.rbkmoney.damsel.identity_document_storage.IdentityDocumentStorageSrv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CdsClientIDStorage {

    private final IdentityDocumentStorageSrv.Iface cdsIDStorageApi;

    public String put(IdentityDocument identity_document) {
        try {
            return cdsIDStorageApi.put(identity_document);
        } catch (TException ex) {
            throw new CdsIDStorageException(String.format("Exception in putIdentityDocument with identity_document: %s", identity_document), ex);
        }
    }

    public IdentityDocument get(String token) {
        log.info("getIdentityDocument: token: {}", token);
        try {
            return cdsIDStorageApi.get(token);
        } catch (TException ex) {
            throw new CdsIDStorageException(String.format("Exception in getIdentityDocument with token: %s", token), ex);
        }
    }

}
