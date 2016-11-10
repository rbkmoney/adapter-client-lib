package com.rbkmoney.cds.client.keyring;

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

    private final static Logger LOGGER = LoggerFactory.getLogger(CdsClientKeyring.class);

    @Autowired
    private KeyringSrv.Iface keyringSrv;

    /**
     * Unlock Keys
     *
     * @param key_share ByteBuffer
     * @return UnlockStatus
     * @throws TException
     */
    public UnlockStatus unlock(ByteBuffer key_share) throws TException {
        LOGGER.info("Keyring: unlock");
        return keyringSrv.unlock(key_share);
    }

    /**
     * Init Keys
     *
     * @param threshold  short
     * @param num_shares short
     * @return List<ByteBuffer>
     * @throws TException
     */
    public List<ByteBuffer> init(short threshold, short num_shares) throws TException {
        LOGGER.info("Keyring: init");
        return keyringSrv.init(threshold, num_shares);
    }

}
