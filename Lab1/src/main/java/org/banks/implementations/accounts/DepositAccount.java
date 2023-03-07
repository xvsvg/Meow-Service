package org.banks.implementations.accounts;

import org.banks.contracts.accounts.BankAccount;
import org.banks.implementations.banks.Bank;
import org.banks.implementations.clients.RussianClient;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public class DepositAccount extends BankAccount {
    public DepositAccount(Bank bank, RussianClient client, double firstDeposit, boolean isOverdraftEnabled, @Nullable Date expirationDate) {
        super(bank, client, firstDeposit, isOverdraftEnabled, expirationDate);
    }

    @Override
    public double getInterestRate(){
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
