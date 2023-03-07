package org.banks.exceptions;

import org.banks.contracts.clients.Client;

public class ClientIdException extends BankApplicationException{

    private ClientIdException() {
        super();
    }

    private ClientIdException(String message) {
        super(message);
    }

    private ClientIdException(String message, Throwable cause) {
        super(message, cause);
    }

    private ClientIdException(Throwable cause) {
        super(cause);
    }

    public static ClientIdException InvalidNumber(){
        return new ClientIdException();
    }
}
