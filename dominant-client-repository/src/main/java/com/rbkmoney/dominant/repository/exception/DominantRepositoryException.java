package com.rbkmoney.dominant.repository.exception;


public class DominantRepositoryException extends RuntimeException {

    public DominantRepositoryException(String message) {
        super(message);
    }

    public DominantRepositoryException(Throwable cause) {
        super(cause);
    }

    public DominantRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
