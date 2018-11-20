package com.rbkmoney.cds.client.keyring;

import com.rbkmoney.cds.client.keyring.exception.CdsKeyringException;
import com.rbkmoney.damsel.cds.KeyringSrv;
import com.rbkmoney.damsel.cds.UnlockStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CdsClientKeyring {

    private final KeyringSrv.Iface keyringSrv;

    public UnlockStatus unlock(ByteBuffer key_share) {
        log.info("Keyring: unlock start");
        try {
            UnlockStatus unlockStatus = keyringSrv.unlock(key_share);
            log.info("Keyring: unlock finish");
            return unlockStatus;
        } catch (TException ex) {
            throw new CdsKeyringException("Keyring. Exception - unlock", ex);
        }
    }

    public List<ByteBuffer> init(short threshold, short num_shares) {
        log.info("Keyring: init start");
        try {
            List<ByteBuffer> list = keyringSrv.init(threshold, num_shares);
            log.info("Keyring: init finish");
            return list;
        } catch (TException ex) {
            throw new CdsKeyringException("Keyring. Exception - init", ex);
        }
    }

    public void lock() {
        log.info("Keyring: lock start");
        try {
            keyringSrv.lock();
            log.info("Keyring: lock finish");
        } catch (TException ex) {
            throw new CdsKeyringException("Keyring. Exception - lock", ex);
        }
    }

    public void rotate() {
        log.info("Keyring: rotate start");
        try {
            keyringSrv.rotate();
            log.info("Keyring: rotate finish");
        } catch (TException ex) {
            throw new CdsKeyringException("Keyring. Exception - rotate", ex);
        }
    }

}
