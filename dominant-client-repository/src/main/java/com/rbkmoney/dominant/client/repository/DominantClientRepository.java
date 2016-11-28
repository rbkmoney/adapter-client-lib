package com.rbkmoney.dominant.client.repository;

import com.rbkmoney.damsel.domain_config.*;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DominantClientRepository implements RepositorySrv.Iface {

    private final static Logger LOGGER = LoggerFactory.getLogger(DominantClientRepository.class);

    @Autowired
    private RepositorySrv.Iface repositorySrv;

    @Override
    public long commit(long version, Commit commit) throws VersionNotFound, OperationConflict, TException {
        LOGGER.info("Dominant: commit start with version {}" , version);
        long responseCommit = repositorySrv.commit(version, commit);
        LOGGER.info("Dominant: commit finish with response commit version {}", responseCommit);
        return responseCommit;
    }

    @Override
    public Snapshot checkout(Reference reference) throws VersionNotFound, TException {
        LOGGER.info("Dominant: checkout start");
        Snapshot snapshot = repositorySrv.checkout(reference);
        LOGGER.info("Dominant: checkout finish");
        return snapshot;
    }

    @Override
    public Map<Long, Commit> pull(long version) throws VersionNotFound, TException {
        LOGGER.info("Dominant: pull start with version {}", version);
        Map<Long, Commit> map = repositorySrv.pull(version);
        LOGGER.info("Dominant: pull finish");
        return map;
    }

}
