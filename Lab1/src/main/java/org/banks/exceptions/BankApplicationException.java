package org.banks.exceptions;

/**
 * Represents of bank application
 * {@inheritDoc}
 */
public class BankApplicationException extends RuntimeException {


    /**
     * {@inheritDoc}
     */
    public BankApplicationException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public BankApplicationException(String message){
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public BankApplicationException(String message, Throwable cause){
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public BankApplicationException(Throwable cause){
        super(cause);
    }
}
