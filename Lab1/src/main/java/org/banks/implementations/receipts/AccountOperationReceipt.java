package org.banks.implementations.receipts;

import lombok.Getter;
import org.banks.contracts.operations.AccountOperation;
import org.banks.contracts.receipts.Receipt;
import org.banks.implementations.operations.OperationOrder;

import java.util.Collection;

public class AccountOperationReceipt extends Receipt {


    public AccountOperationReceipt(double total, String details, AccountOperation operation, Collection<OperationOrder> orders) {
        super(total, details, operation, orders);
    }
}
