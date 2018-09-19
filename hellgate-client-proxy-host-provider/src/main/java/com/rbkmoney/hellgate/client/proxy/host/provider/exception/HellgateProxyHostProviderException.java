package com.rbkmoney.hellgate.client.proxy.host.provider.exception;

/**
 * Handy class for wrapping runtime {@code Exceptions} with a root cause.
 *
 * @author Anatoly Cherkasov
 * @see #getMessage()
 * @see #printStackTrace
 */
public class HellgateProxyHostProviderException extends RuntimeException {

    /**
     * Constructs a new {@code HellgateProxyHostProviderException} with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public HellgateProxyHostProviderException() {
        super();
    }

    /**
     * Construct a new {@code HellgateProxyHostProviderException} with the specified detail message.
     *
     * @param message the detail message
     */
    public HellgateProxyHostProviderException(String message) {
        super(message);
    }

    /**
     * Construct a new {@code HellgateProxyHostProviderException} with the cause.
     *
     * @param cause the root cause
     */
    public HellgateProxyHostProviderException(Throwable cause) {
        super(cause);
    }

    /**
     * Construct a new {@code HellgateProxyHostProviderException} with the
     * specified detail message and root cause.
     *
     * @param message the detail message
     * @param cause   the root cause
     */
    public HellgateProxyHostProviderException(String message, Throwable cause) {
        super(message, cause);
    }

}
