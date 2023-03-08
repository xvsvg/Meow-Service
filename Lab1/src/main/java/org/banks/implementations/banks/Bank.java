package org.banks.implementations.banks;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.Getter;
import org.banks.contracts.operations.AccountOperation;
import org.banks.contracts.accounts.BankAccount;
import org.banks.contracts.clients.Client;
import org.banks.contracts.clients.ClientId;
import org.banks.contracts.receipts.Receipt;
import org.banks.exceptions.ReceiptException;
import org.banks.implementations.accounts.CreditAccount;
import org.banks.implementations.banks.tools.BankAccountInfo;
import org.banks.implementations.banks.tools.BankConfiguration;
import org.banks.implementations.banks.tools.PercentsAndFees;
import org.banks.implementations.operations.OperationOrder;
import org.banks.implementations.receipts.AccountOperationReceipt;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents bank
 */
public class Bank {
    private final HashMap<Client, LinkedList<BankAccountInfo>> accounts;

    /**
     * Returns central bank to which bank assigned
     */
    @Getter
    private final CentralBank centralBank;

    /**
     * Returns bank configuration
     *
     * @see org.banks.implementations.banks.tools.BankConfiguration
     */
    @Getter
    private final BankConfiguration bankConfiguration;

    /**
     * Returns bank id
     */
    @Getter
    private final UUID id;

    /**
     * Returns daily event handler
     */
    @Getter
    private final Observer<Bank> dayChangeHandler;

    /**
     * Returns monthly event handler
     */
    @Getter
    private final Observer<Bank> monthChangeHandler;

    /**
     * Construct bank instance
     * @param centralBank central bank in which bank will be registered
     * @param bankConfiguration configuration of a bank
     */
    public Bank(CentralBank centralBank, BankConfiguration bankConfiguration) {

        this.centralBank = centralBank;
        this.bankConfiguration = bankConfiguration;

        this.id = UUID.randomUUID();
        this.accounts = new HashMap<Client, LinkedList<BankAccountInfo>>();

        dayChangeHandler = new Observer<Bank>() {

            @Override
            public void onNext(Bank bank) {
                calculateFee();
                calculateInterest();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
            }
        };

        monthChangeHandler = new Observer<Bank>() {

            @Override
            public void onNext(Bank bank) {
                chargeFee();
                accrueInterest();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
            }
        };
    }

    /**
     * @return Common interest rate
     */
    public double getInterestRate() {
        return this.bankConfiguration.getCommonInterestRate();
    }

    /**
     * @return Common charge rate
     */
    public double getChargeRate() {
        return this.bankConfiguration.getCommonInterestRate();
    }

    /**
     * @return Bunch of clients registered in a bank
     */
    public Collection<Client> getClients() {
        return Collections.unmodifiableSet(this.accounts.keySet());
    }

    /**
     * @return Bunch of accounts registered in a bank
     */
    public Collection<BankAccountInfo> getAccounts() {
        return this.accounts.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Registers bank account
     *
     * @param account account to be registered
     * @return registered bank account
     */
    public BankAccount addBankAccount(BankAccount account) {
        if (this.accounts.containsKey(account.getClient())) {
            var list = this.accounts.get(account.getClient());
            list.add(new BankAccountInfo(new PercentsAndFees(0, 0), account));
            this.accounts.put(account.getClient(), list);
        } else {
            this.accounts.put(account.getClient(), new LinkedList<BankAccountInfo>(
                    Arrays.asList(new BankAccountInfo(
                            new PercentsAndFees(0, 0),
                            account
                    ))
            ));
        }
        return account;
    }

    /**
     * Removes bank account from registered ones
     *
     * @param account account to be removed
     */
    public void removeBankAccount(BankAccount account) {
        var list = this.accounts.get(account.getClient());
        list.remove(account);
        this.accounts.put(account.getClient(), list);
    }

    /**
     * Finds client bank accounts in a bank
     *
     * @param id id of a client, whose bank accounts need to be found
     * @return Bunch of client accounts
     */
    public Collection<BankAccountInfo> findBankAccount(ClientId id) {
        return this.accounts.entrySet().stream()
                .filter(a -> a.getKey().getId() != null && a.getKey().getId().getIdNumber() == id.getIdNumber())
                .flatMap(a -> a.getValue().stream())
                .collect(Collectors.toList());
    }

    /**
     * Performs transaction
     *
     * @param operation operation, that should be executed
     */
    public void makeTransaction(AccountOperation operation) {
        operation.evaluate();

        for (OperationOrder order : operation.getOrders()) {
            updateAccountHistory(order.from(), operation);

            if (order.from().equals(order.to()) == false) {
                updateAccountHistory(order.to(), operation);
            }
        }
    }

    /**
     * Resets effect of performed transaction
     *
     * @param receipt receipt of existing transaction
     * @throws ReceiptException iff you're trying to reset folded operation
     */
    public void foldTransaction(Receipt receipt) throws ReceiptException {
        if (receipt.isCancelled()) {
            throw ReceiptException.unableToFoldTransaction();
        }

        receipt.getOperation().reset();
        receipt.setCancelled(false);
    }

    /**
     * Updates account history
     *
     * @param account   account, that history will be updated
     * @param operation operation, that made using the account
     */
    private void updateAccountHistory(BankAccount account, AccountOperation operation) {
        account.addToHistory(new AccountOperationReceipt(
                operation.getTotal(),
                String.format("Operation was made with bank %s on %s.", this, LocalDateTime.now()),
                operation,
                operation.getOrders()));
    }

    /**
     * Estimates interest for all accounts
     */
    private void calculateInterest() {
        this.accounts.values().stream()
                .flatMap(Collection::stream)
                .filter(acc -> !(acc.account() instanceof CreditAccount))
                .forEach(acc -> {
                    acc.percentsAndFees().setPercent(acc.percentsAndFees().getPercent()
                            + Math.round((acc.account().getInterestRate() / 365 / 100) * acc.account().getMoney() * 100.0) / 100.0);
                    acc.percentsAndFees().setFee(acc.percentsAndFees().getFee()
                            + Math.round((acc.account().getInterestRate() / 365 / 100) * acc.account().getMoney() * 100.0) / 100.0);
                });
    }

    /**
     * Estimates fee for all accounts
     */
    private void calculateFee() {
        this.accounts.values().stream()
                .flatMap(Collection::stream)
                .filter(acc -> acc.account() instanceof CreditAccount && acc.account().getMoney() < 0)
                .forEach(acc -> acc.percentsAndFees().setFee(acc.percentsAndFees().getFee()
                        + Math.round((acc.account().getChargeRate() / 365 / 100) * Math.abs(acc.account().getMoney()) * 100.0) / 100.0));
    }

    /**
     * Accrues estimated interest for all accounts
     */
    private void accrueInterest() {
        this.accounts.values().stream()
                .flatMap(Collection::stream)
                .filter(acc -> !(acc.account() instanceof CreditAccount))
                .forEach(acc -> {
                    acc.account().setMoney(acc.account().getMoney() + acc.percentsAndFees().getPercent());
                    acc.percentsAndFees().setPercent(0);
                });
    }

    /**
     * Charges estimated charges for all accounts
     */
    private void chargeFee() {
        this.accounts.values().stream()
                .flatMap(Collection::stream)
                .filter(acc -> acc.account() instanceof CreditAccount)
                .forEach(acc -> {
                    acc.account().setMoney(acc.account().getMoney() - acc.percentsAndFees().getFee());
                    acc.percentsAndFees().setFee(0);
                });
    }
}
