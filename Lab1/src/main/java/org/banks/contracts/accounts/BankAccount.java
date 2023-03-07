package org.banks.contracts.accounts;

import lombok.Getter;

import java.util.*;

import lombok.Setter;
import org.banks.contracts.clients.*;
import org.banks.implementations.banks.Bank;
import org.banks.contracts.receipts.*;
import org.jetbrains.annotations.Nullable;

public abstract class BankAccount {
    private final LinkedList<Receipt> receipts;

    @Getter
    private Date expirationDate;

    @Getter
    private Bank bank;

    @Getter
    @Setter
    private double money;

    @Getter
    private boolean overdraft;

    @Getter
    private Client client;

    @Getter
    private UUID id;

    private boolean isSuspicious;

    public BankAccount(
            Bank bank,
            Client client,
            double firstDeposit,
            boolean isOverdraftEnabled,
            @Nullable Date expirationDate
    ) {
        this.bank = bank;
        this.client = client;
        this.money = firstDeposit;
        this.overdraft = isOverdraftEnabled;
        this.expirationDate = expirationDate;

        this.id = UUID.randomUUID();
        receipts = new LinkedList<Receipt>();
    }

    public boolean equals(@Nullable BankAccount account) {
        return account != null && this.id.equals(account.getId());
    }

    public Collection<Receipt> getHistory() {
        return Collections.unmodifiableList(this.receipts);
    }

    public double getInterestRate() {
        return this.bank.getInterestRate();
    }

    public double getChargeRate() {
        return this.bank.getChargeRate();
    }

    public boolean getStatus() {
        return this.client.getIsSuspicious();
    }

    public Receipt addToHistory(Receipt receipt) {
        this.receipts.add(receipt);

        return receipt;
    }
}
