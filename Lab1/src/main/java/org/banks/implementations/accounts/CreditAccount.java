package org.banks.implementations.accounts;

import org.banks.contracts.accounts.BankAccount;
import org.banks.contracts.clients.Client;
import org.banks.implementations.banks.Bank;

import java.time.LocalTime;

/**
 * {@inheritDoc}
 */
public class CreditAccount extends BankAccount {

    /**
     * {@inheritDoc}
     */
    public CreditAccount(Bank bank, Client client, double firstDeposit, LocalTime expirationDate) {
        super(bank, client, firstDeposit, true, expirationDate);
    }
}
