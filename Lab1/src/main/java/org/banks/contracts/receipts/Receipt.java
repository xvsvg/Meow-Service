package org.banks.contracts.receipts;

import lombok.Getter;
import org.banks.contracts.operations.AccountOperation;
import org.banks.exceptions.ReceiptException;
import org.banks.implementations.operations.OperationOrder;

import java.util.Collection;

/**
 * Represents operation receipt
 */
public abstract class Receipt {

    /**
     * Total money amount of an account operation
     */
    @Getter
    private double total;

    /**
     * Details about transaction
     */
    @Getter
    private final String details;

    /**
     * Account operation to which receipt is linked
     */
    @Getter
    private final AccountOperation operation;

    /**
     * Order in which operation was performed
     */
    @Getter
    private final Collection<OperationOrder> orders;

    /**
     * Whether operation is cancelled
     */
    @Getter
    private boolean isCancelled;

    /**
     * Constructs receipt instance
     * @param total total money amount of a receipt
     * @param details details about operation
     * @param operation executed operation
     * @param orders account order in which operation was performed
     * @throws ReceiptException iff money amount is negative
     */
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

    /**
     * Sets total money amount to receipt
     *
     * @param value money amount to be set
     * @throws ReceiptException iff value is negative
     */
    public void setTotal(double value) throws ReceiptException {
        if (value <= 0) {
            throw ReceiptException.NegativeMoneyAmountException();
        }
        this.total = value;
    }

    /**
     * Sets status to receipt, where {@code setCancelled(true)}
     * means that operation was cancelled
     *
     * @param value status to be set
     * @throws ReceiptException iff operation was cancelled, but needs to be restored
     */
    public void setCancelled(boolean value) throws ReceiptException {
        if (isCancelled == true && value == false)
            throw ReceiptException.UnableToChangeStatusException();

        this.isCancelled = value;
    }
}
