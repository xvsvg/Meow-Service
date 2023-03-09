package org.banks.implementations.accounts;

import org.banks.contracts.accounts.BankAccount;
import org.banks.contracts.clients.Client;
import org.banks.implementations.banks.Bank;

import java.time.LocalTime;

/**
 * {@inheritDoc}
 */
public class DebitAccount extends BankAccount {

    /**
     * {@inheritDoc}
     */
    public DebitAccount(Bank bank, Client client, double firstDeposit, boolean isOverdraftEnabled, LocalTime expirationDate) {
        super(bank, client, firstDeposit, isOverdraftEnabled, expirationDate);
    }
}
