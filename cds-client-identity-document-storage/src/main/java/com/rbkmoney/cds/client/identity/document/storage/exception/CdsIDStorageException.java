package com.rbkmoney.cds.client.identity.document.storage.exception;

/**
 * Handy class for wrapping runtime {@code Exceptions} with a root cause.
 *
 * @author Anatoly Cherkasov
 * @see #getMessage()
 * @see #printStackTrace
 */
public class CdsIDStorageException extends RuntimeException {

    /**
     * Constructs a new {@code CdsIDStorageException} with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CdsIDStorageException() {
        super();
    }

    /**
     * Construct a new {@code CdsIDStorageException} with the specified detail message.
     *
     * @param message the detail message
     */
    public CdsIDStorageException(String message) {
        super(message);
    }

    /**
     * Construct a new {@code CdsIDStorageException} with the cause.
     *
     * @param cause the root cause
     */
    public CdsIDStorageException(Throwable cause) {
        super(cause);
    }

    /**
     * Construct a new {@code CdsIDStorageException} with the
     * specified detail message and root cause.
     *
     * @param message the detail message
     * @param cause   the root cause
     */
    public CdsIDStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
