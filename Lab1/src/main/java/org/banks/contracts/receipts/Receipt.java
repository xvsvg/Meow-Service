package org.banks.contracts.receipts;

import lombok.Getter;
import org.banks.contracts.operations.AccountOperation;
import org.banks.exceptions.ReceiptException;
import org.banks.implementations.operations.OperationOrder;

import java.util.Collection;

public abstract class Receipt {

    @Getter
    private double total;

    @Getter
    private final String details;

    @Getter
    private final AccountOperation operation;

    @Getter
    private final Collection<OperationOrder> orders;

    @Getter
    private boolean isCancelled;

    public Receipt(double total, String details, AccountOperation operation, Collection<OperationOrder> orders) throws ReceiptException {
        if (total <= 0) {
            throw ReceiptException.NegativeMoneyAmountException();
        }
        this.total = total;
        this.details = details;
        this.operation = operation;
        this.orders = orders;
        this.isCancelled = false;
    }

    public void setTotal(double value) throws ReceiptException {
        if (value <= 0) {
            throw ReceiptException.NegativeMoneyAmountException();
        }
        this.total = value;
    }

    public void setCancelled(boolean value) throws ReceiptException {
        if (isCancelled == true && value == false)
            throw ReceiptException.UnableToChangeStatusException();

        this.isCancelled = value;
    }
}
