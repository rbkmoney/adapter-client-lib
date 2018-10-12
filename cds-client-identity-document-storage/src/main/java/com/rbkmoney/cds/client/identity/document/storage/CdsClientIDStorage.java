package com.rbkmoney.cds.client.identity.document.storage;

import com.rbkmoney.cds.client.identity.document.storage.exception.CdsIDStorageException;
import com.rbkmoney.damsel.identity_document_storage.IdentityDocument;
import com.rbkmoney.damsel.identity_document_storage.IdentityDocumentStorageSrv;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CdsClientIDStorage {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final IdentityDocumentStorageSrv.Iface cdsIDStorageApi;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link CdsClientIDStorage} instance with the given
     * initial parameters to be constructed.
     *
     * @param cdsIDStorageApi the field's cdsIDStorageApi (see {@link #cdsIDStorageApi}).
     */
    @Autowired
    public CdsClientIDStorage(IdentityDocumentStorageSrv.Iface cdsIDStorageApi) {
        this.cdsIDStorageApi = cdsIDStorageApi;
    }


    // ------------------------------------------------------------------------
    // Public methods
    // ------------------------------------------------------------------------

    public String put(IdentityDocument identity_document) {
        log.info("putIdentityDocument: identity_document: {}", identity_document);
        try {
            String response = cdsIDStorageApi.put(identity_document);
            log.info("putIdentityDocument: response {}, identity_document: {}", response, identity_document);
            return response;
        } catch (TException ex) {
            throw new CdsIDStorageException(String.format("Exception in putIdentityDocument with identity_document: %s", identity_document), ex);
        }
    }

    public IdentityDocument get(String token) {
        log.info("getIdentityDocument: token: {}", token);
        try {
            IdentityDocument identityDocument = cdsIDStorageApi.get(token);
            log.info("getIdentityDocument: response, token: {}", token);
            return identityDocument;
        } catch (TException ex) {
            throw new CdsIDStorageException(String.format("Exception in getIdentityDocument with token: %s", token), ex);
        }
    }

}
