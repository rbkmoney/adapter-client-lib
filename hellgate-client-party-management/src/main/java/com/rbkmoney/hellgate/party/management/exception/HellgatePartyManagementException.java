package com.rbkmoney.hellgate.party.management.exception;


public class HellgatePartyManagementException extends RuntimeException {

    public HellgatePartyManagementException(String message) {
        super(message);
    }

    public HellgatePartyManagementException(Throwable cause) {
        super(cause);
    }

    public HellgatePartyManagementException(String message, Throwable cause) {
        super(message, cause);
    }

}
