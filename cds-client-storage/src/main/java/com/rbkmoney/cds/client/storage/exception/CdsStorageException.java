package com.rbkmoney.cds.client.storage.exception;

/**
 * Handy class for wrapping runtime {@code Exceptions} with a root cause.
 *
 * @author Anatoly Cherkasov
 * @see #getMessage()
 * @see #printStackTrace
 */
public class CdsStorageException extends RuntimeException {

    /**
     * Constructs a new {@code CdsStorageException} with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CdsStorageException() {
        super();
    }

    /**
     * Construct a new {@code CdsStorageException} with the specified detail message.
     *
     * @param message the detail message
     */
    public CdsStorageException(String message) {
        super(message);
    }

    /**
     * Construct a new {@code CdsStorageException} with the cause.
     *
     * @param cause the root cause
     */
    public CdsStorageException(Throwable cause) {
        super(cause);
    }

    /**
     * Construct a new {@code CdsStorageException} with the
     * specified detail message and root cause.
     *
     * @param message the detail message
     * @param cause   the root cause
     */
    public CdsStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
