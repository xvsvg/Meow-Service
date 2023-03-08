package org.banks.exceptions;

/**
 * {@inheritDoc}
 */
public class BankAccountException extends BankApplicationException {

    private BankAccountException() {
        super();
    }

    private BankAccountException(String message) {
        super(message);
    }

    private BankAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    private BankAccountException(Throwable cause) {
        super(cause);
    }

    /**
     * Rate amount is invalid
     * @param message details
     * @return BankAccountException instance
     */
    public static BankAccountException NegativeRateAmountException(String message) {
        return new BankAccountException(message);
    }

    /**
     * Charges amount is invalid
     * @param message details
     * @return BankAccountException instance
     */
    public static BankAccountException NegativeChargesAmountException(String message) {
        return new BankAccountException(message);
    }
}
