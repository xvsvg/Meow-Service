package org.banks.exceptions;

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

    public static AccountOperationException AccountIsSuspiciousException(String message){
        return new AccountOperationException(message);
    }
}
