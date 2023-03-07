package org.banks.contracts.operations;

import lombok.Getter;
import org.banks.implementations.operations.OperationOrder;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public abstract class AccountOperation {
    @Getter
    private double total;

    private LinkedList<OperationOrder> orders;

    public Collection<OperationOrder> getOrders(){
        return Collections.unmodifiableList(this.orders);
    }
    public abstract void evaluate();
    public abstract void reset();
}
