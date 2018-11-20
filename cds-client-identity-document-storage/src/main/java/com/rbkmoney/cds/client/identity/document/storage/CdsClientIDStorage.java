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

    public String put(IdentityDocument identityDocument) {
        try {
            return cdsIDStorageApi.put(identityDocument);
        } catch (TException ex) {
            throw new CdsIDStorageException("Failed to put identity document into cds storage", ex);
        }
    }

    public IdentityDocument get(String token) {
        log.info("getIdentityDocument: token: {}", token);
        try {
            return cdsIDStorageApi.get(token);
        } catch (TException ex) {
            throw new CdsIDStorageException(String.format("Failed to get identity document into cds storage with token: %s", token), ex);
        }
    }

}
