package org.banks.exceptions;

public class BankApplicationException extends RuntimeException {

    public BankApplicationException() {
        super();
    }

    public BankApplicationException(String message){
        super(message);
    }

    public BankApplicationException(String message, Throwable cause){
        super(message, cause);
    }

    public BankApplicationException(Throwable cause){
        super(cause);
    }
}
