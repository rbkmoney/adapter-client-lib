package com.rbkmoney.cds.client.keyring.exception;

/**
 * Handy class for wrapping runtime {@code Exceptions} with a root cause.
 *
 * @author Anatoly Cherkasov
 * @see #getMessage()
 * @see #printStackTrace
 */
public class CdsKeyringException extends RuntimeException {

    /**
     * Constructs a new {@code CdsKeyringException} with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CdsKeyringException() {
        super();
    }

    /**
     * Construct a new {@code CdsKeyringException} with the specified detail message.
     *
     * @param message the detail message
     */
    public CdsKeyringException(String message) {
        super(message);
    }

    /**
     * Construct a new {@code CdsKeyringException} with the cause.
     *
     * @param cause the root cause
     */
    public CdsKeyringException(Throwable cause) {
        super(cause);
    }

    /**
     * Construct a new {@code CdsKeyringException} with the
     * specified detail message and root cause.
     *
     * @param message the detail message
     * @param cause   the root cause
     */
    public CdsKeyringException(String message, Throwable cause) {
        super(message, cause);
    }

}
