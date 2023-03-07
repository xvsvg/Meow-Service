package org.banks.exceptions;

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

    public static BankAccountException NegativeRateAmountException(String message) {
        return new BankAccountException(message);
    }

    public static BankAccountException NegativeChargesAmountException(String message) {
        return new BankAccountException(message);
    }
}
