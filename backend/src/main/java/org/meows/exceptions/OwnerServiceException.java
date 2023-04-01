package org.meows.exceptions;

public class OwnerServiceException extends RuntimeException {

    public OwnerServiceException() {
        super();
    }

    public OwnerServiceException(String message) {
        super(message);
    }

    public OwnerServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
