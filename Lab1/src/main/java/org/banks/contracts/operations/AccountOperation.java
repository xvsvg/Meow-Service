package org.banks.contracts.operations;

import lombok.Getter;
import org.banks.implementations.operations.OperationOrder;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Represents account operation
 */
public abstract class AccountOperation {

    /**
     * Total money amount for operation
     */
    @Getter
    private double total;

    private LinkedList<OperationOrder> orders;

    /**
     * @return Order in which this operation should be executed
     */
    public Collection<OperationOrder> getOrders() {
        return Collections.unmodifiableList(this.orders);
    }

    /**
     * Performs an operation
     */
    public abstract void evaluate();

    /**
     * Resets an operation effect
     */
    public abstract void reset();
}
