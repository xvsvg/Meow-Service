package org.banks.contracts.accounts;

import lombok.Getter;

import java.util.*;

import lombok.Setter;
import org.banks.contracts.clients.*;
import org.banks.implementations.banks.Bank;
import org.banks.contracts.receipts.*;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a bank account
 */
public abstract class BankAccount {
    private final LinkedList<Receipt> receipts;

    /**
     * @return Account expiration date
     */
    @Getter
    private Date expirationDate;

    /**
     * @return Bank in which account is registered
     */
    @Getter
    private Bank bank;

    /**
     * Returns accounts' money amount
     * <div class="setterDocs">
     * Sets accounts' money amount
     * </div>
     *
     * @param money Amount of money to be set
     * @return Current balance
     */
    @Getter
    @Setter
    private double money;

    /**
     * @return Whether bank account has overdraft enabled
     */
    @Getter
    private boolean overdraft;

    /**
     * @return Bank account owner
     */
    @Getter
    private Client client;

    /**
     * @return Bank account ID
     */
    @Getter
    private UUID id;

    private boolean isSuspicious;

    /**
     * Constructs bank account instance
     * @param bank bank in which account will be registered
     * @param client client to which account will be assigned
     * @param firstDeposit first deposit
     * @param isOverdraftEnabled true -- enables overdraft, false -- disables
     * @param expirationDate date on which account will expire
     */
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

    /**
     * @param account Comparison account.
     * @return Whether provided bank accounts are the same one
     */
    public boolean equals(@Nullable BankAccount account) {
        return account != null && this.id.equals(account.getId());
    }

    /**
     * @return Accounts' operation history
     */
    public Collection<Receipt> getHistory() {
        return Collections.unmodifiableList(this.receipts);
    }

    /**
     * @return Interest rate of an account
     */
    public double getInterestRate() {
        return this.bank.getInterestRate();
    }

    /**
     * @return Charge rate of an accounts
     */
    public double getChargeRate() {
        return this.bank.getChargeRate();
    }

    /**
     * @return Whether account is suspicious
     * @see org.banks.contracts.clients.Client
     */
    public boolean getStatus() {
        return this.client.getIsSuspicious();
    }

    /**
     * Adds operation receipt to account history
     *
     * @param receipt receipt to be added
     * @return Added receipt
     */
    public Receipt addToHistory(Receipt receipt) {
        this.receipts.add(receipt);

        return receipt;
    }
}
