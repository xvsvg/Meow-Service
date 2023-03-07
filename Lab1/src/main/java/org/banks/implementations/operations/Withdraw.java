package org.banks.implementations.operations;

import lombok.Getter;
import org.banks.contracts.accounts.BankAccount;
import org.banks.contracts.operations.AccountOperation;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class Withdraw extends AccountOperation {

    @Getter
    private final BankAccount account;

    @Getter
    private final double total;

    public Withdraw(BankAccount account, double total) {

        this.account = account;
        this.total = total;
    }

    public Collection<OperationOrder> getOrders() {
        return new LinkedList<OperationOrder>(Arrays.asList(
                new OperationOrder(this.account, this.account)));
    }

    @Override
    public void evaluate() {
        this.account.setMoney(account.getMoney() - total);
    }

    @Override
    public void reset() {
        this.account.setMoney(account.getMoney() + total);
    }
}
