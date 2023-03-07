package org.banks.exceptions;

public class ReceiptException extends BankApplicationException{

    private ReceiptException(){
        super();
    }

    private ReceiptException(String message){
        super(message);
    }

    private ReceiptException(String message, Throwable cause){
        super(message, cause);
    }

    private ReceiptException(Throwable cause){
        super(cause);
    }

    public static ReceiptException NegativeMoneyAmountException(){
        return new ReceiptException();
    }

    public static ReceiptException UnableToChangeStatusException(){
        return new ReceiptException();
    }

    public static ReceiptException unableToFoldTransaction(){
        return new ReceiptException();
    }
}
