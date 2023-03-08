package org.banks.implementations.accounts;

import org.banks.contracts.accounts.BankAccount;
import org.banks.implementations.banks.Bank;
import org.banks.implementations.clients.RussianClient;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * {@inheritDoc}
 */
public class CreditAccount extends BankAccount {

    /**
     * {@inheritDoc}
     */
    public CreditAccount(Bank bank, RussianClient client, double firstDeposit, boolean isOverdraftEnabled, @Nullable Date expirationDate) {
        super(bank, client, firstDeposit, true, expirationDate);
    }
}
