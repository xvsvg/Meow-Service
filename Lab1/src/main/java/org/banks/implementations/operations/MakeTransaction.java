package org.banks.implementations.operations;

import lombok.Getter;
import org.banks.contracts.operations.AccountOperation;
import org.banks.exceptions.AccountOperationException;

import java.util.Collection;

public class MakeTransaction extends AccountOperation {

    @Getter
    private final Collection<OperationOrder> orders;

    @Getter
    private final double total;

    public MakeTransaction(Collection<OperationOrder> orders, double total) {

        this.orders = orders;
        this.total = total;
    }

    @Override
    public void evaluate() {
        for (OperationOrder account : orders) {
            if (account.from().getStatus() == true || account.to().getStatus() == true) {
                throw AccountOperationException.AccountIsSuspiciousException("Provide all profile information to use service");
            }
            account.from().setMoney(account.from().getMoney() - total);
            account.to().setMoney(account.to().getMoney() + total);
        }
    }

    @Override
    public void reset() {
        for (OperationOrder account : orders) {
            if (account.from().getStatus() == true || account.to().getStatus() == true) {
                throw AccountOperationException.AccountIsSuspiciousException("Provide all profile information to use service");
            }
            account.from().setMoney(account.from().getMoney() + total);
            account.to().setMoney(account.to().getMoney() - total);
        }
    }
}
