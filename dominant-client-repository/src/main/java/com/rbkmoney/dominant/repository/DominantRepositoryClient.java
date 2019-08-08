package com.rbkmoney.dominant.repository;


import com.rbkmoney.damsel.domain_config.*;
import com.rbkmoney.dominant.repository.exception.DominantRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DominantRepositoryClient implements RepositoryClientSrv.Iface {

    private final RepositoryClientSrv.Iface repositoryClient;

    @Override
    public VersionedObject checkoutObject(Reference reference, com.rbkmoney.damsel.domain.Reference reference1) throws VersionNotFound, ObjectNotFound, TException {
        log.info("checkoutObject by reference1: {}, reference2 {}", reference, reference1);
        try {
            return repositoryClient.checkoutObject(reference, reference1);
        } catch (TException ex) {
            throw new DominantRepositoryException(String.format("Can't checkoutObject DominantRepositoryClient with  reference1: %s, reference2 %s", reference, reference1), ex);
        }
    }
}
