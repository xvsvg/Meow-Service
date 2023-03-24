package org.banks.implementations.accounts;

import org.banks.contracts.accounts.BankAccount;
import org.banks.contracts.clients.Client;
import org.banks.implementations.banks.Bank;

import java.time.LocalTime;

/**
 * {@inheritDoc}
 */
public class DepositAccount extends BankAccount {

    /**
     * {@inheritDoc}
     */
    public DepositAccount(Bank bank, Client client, double firstDeposit, boolean isOverdraftEnabled, LocalTime expirationDate) {
        super(bank, client, firstDeposit, isOverdraftEnabled, expirationDate);
    }

    @Override
    public double getInterestRate() {
        var depositAmounts = this.getBank().getBankConfiguration().getConfiguration().getDeposits().stream().toList();
        var interestRates = this.getBank().getBankConfiguration().getConfiguration().getDeposits().stream().toList();

        for (int i = 1; i < depositAmounts.size(); ++i) {
            if (depositAmounts.get(i - 1).compareTo(getMoney()) < 0 && depositAmounts.get(i).compareTo(getMoney()) < 0) {
                return interestRates.get(i);
            }
        }

        if (getMoney() < depositAmounts.get(0)) {
            return interestRates.get(0);
        } else if (getMoney() > depositAmounts.get(depositAmounts.size() - 1)) {
            return interestRates.get(interestRates.size() - 1);
        }

        throw new UnsupportedOperationException("Invalid deposit account configuration.");
    }
}
