package com.rbkmoney.fistful.client.withdrawal.exception;

public class FistfulClientWithdrawalException extends RuntimeException {

    public FistfulClientWithdrawalException() {
        super();
    }

    public FistfulClientWithdrawalException(String message) {
        super(message);
    }

    public FistfulClientWithdrawalException(Throwable cause) {
        super(cause);
    }

    public FistfulClientWithdrawalException(String message, Throwable cause) {
        super(message, cause);
    }

}
