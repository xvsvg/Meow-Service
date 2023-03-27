package org.meows.exceptions;

public class CatServiceException extends RuntimeException {

    public CatServiceException() {
        super();
    }

    public CatServiceException(String message) {
        super(message);
    }

    public CatServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
