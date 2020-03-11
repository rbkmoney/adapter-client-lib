package com.rbkmoney.fistful.client.exception;

public class FistfulException extends RuntimeException {

    public FistfulException() {
        super();
    }

    public FistfulException(String message) {
        super(message);
    }

    public FistfulException(Throwable cause) {
        super(cause);
    }

    public FistfulException(String message, Throwable cause) {
        super(message, cause);
    }

}
