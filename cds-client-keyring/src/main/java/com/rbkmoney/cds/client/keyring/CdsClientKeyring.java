package com.rbkmoney.cds.client.keyring;

import com.rbkmoney.cds.client.keyring.exception.CdsKeyringException;
import com.rbkmoney.damsel.cds.KeyringSrv;
import com.rbkmoney.damsel.cds.UnlockStatus;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.List;

@Component
public class CdsClientKeyring {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final KeyringSrv.Iface keyringSrv;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link CdsClientKeyring} instance with the given
     * initial parameters to be constructed.
     *
     * @param keyringSrv the field's keyringSrv (see {@link #keyringSrv}).
     */
    @Autowired
    public CdsClientKeyring(KeyringSrv.Iface keyringSrv) {
        this.keyringSrv = keyringSrv;
    }


    /**
     * Unlock Keys
     *
     * @param key_share ByteBuffer
     * @return UnlockStatus
     */
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

    /**
     * Init Keys
     *
     * @param threshold  short
     * @param num_shares short
     * @return List<ByteBuffer>
     */
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

    /**
     * Lock
     */
    public void lock() {
        log.info("Keyring: lock start");
        try {
            keyringSrv.lock();
            log.info("Keyring: lock finish");
        } catch (TException ex) {
            throw new CdsKeyringException("Keyring. Exception - lock", ex);
        }
    }

    /**
     * Rotate
     */
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
