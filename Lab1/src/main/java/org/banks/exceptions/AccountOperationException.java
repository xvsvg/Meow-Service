package org.banks.exceptions;

/**
 * {@inheritDoc}
 */
public class AccountOperationException extends BankApplicationException{
    private AccountOperationException() {
        super();
    }

    private AccountOperationException(String message) {
        super(message);
    }

    private AccountOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    private AccountOperationException(Throwable cause) {
        super(cause);
    }

    /**
     * Account is suspicious, so operation cannot be executed
     * @param message details
     * @return AccountOperationException instance
     */
    public static AccountOperationException AccountIsSuspiciousException(String message){
        return new AccountOperationException(message);
    }
}
