package org.banks.exceptions;

/**
 * {@inheritDoc}
 */
public class ReceiptException extends BankApplicationException {

    private ReceiptException() {
        super();
    }

    private ReceiptException(String message) {
        super(message);
    }

    private ReceiptException(String message, Throwable cause) {
        super(message, cause);
    }

    private ReceiptException(Throwable cause) {
        super(cause);
    }

    /**
     * Invalid money amount
     *
     * @return NegativeMoneyAmountException instance
     */
    public static ReceiptException NegativeMoneyAmountException() {
        return new ReceiptException();
    }

    /**
     * Invalid action applied to status
     *
     * @return ReceiptException instance
     */
    public static ReceiptException UnableToChangeStatusException() {
        return new ReceiptException();
    }

    /**
     * Invalid action applied to transaction
     *
     * @return ReceiptException instance
     */
    public static ReceiptException unableToFoldTransaction() {
        return new ReceiptException();
    }
}
