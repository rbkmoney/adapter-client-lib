package com.rbkmoney.bender.client.exception;

public class BenderException extends RuntimeException {

    public BenderException() {
        super();
    }

    public BenderException(String message) {
        super(message);
    }

    public BenderException(Throwable cause) {
        super(cause);
    }

    public BenderException(String message, Throwable cause) {
        super(message, cause);
    }

}
