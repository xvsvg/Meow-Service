package org.banks.implementations.accounts;

import org.banks.contracts.accounts.BankAccount;
import org.banks.implementations.banks.Bank;
import org.banks.implementations.clients.RussianClient;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public class CreditAccount extends BankAccount {
    public CreditAccount(Bank bank, RussianClient client, double firstDeposit, boolean isOverdraftEnabled, @Nullable Date expirationDate) {
        super(bank, client, firstDeposit, true, expirationDate);
    }
}
