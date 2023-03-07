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


public class Bank {
    private final HashMap<Client, LinkedList<BankAccountInfo>> accounts;

    @Getter
    private final CentralBank centralBank;

    @Getter
    private final BankConfiguration bankConfiguration;

    @Getter
    private final UUID id;

    @Getter
    private final Observer<Bank> dayChangeHandler;

    @Getter
    private final Observer<Bank> monthChangeHandler;

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


    public double getInterestRate() {
        return this.bankConfiguration.getCommonInterestRate();
    }

    public double getChargeRate() {
        return this.bankConfiguration.getCommonInterestRate();
    }

    public Collection<Client> getClients() {
        return Collections.unmodifiableSet(this.accounts.keySet());
    }

    public Collection<BankAccountInfo> getAccounts() {
        return this.accounts.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedList::new));
    }

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

    public void removeBankAccount(BankAccount account) {
        var list = this.accounts.get(account.getClient());
        list.remove(account);
        this.accounts.put(account.getClient(), list);
    }

    public Collection<BankAccountInfo> findBankAccount(ClientId id) {
        return this.accounts.entrySet().stream()
                .filter(a -> a.getKey().getId() != null && a.getKey().getId().getIdNumber() == id.getIdNumber())
                .flatMap(a -> a.getValue().stream())
                .collect(Collectors.toList());
    }

    public void makeTransaction(AccountOperation operation) {
        operation.evaluate();

        for (OperationOrder order : operation.getOrders()) {
            updateAccountHistory(order.from(), operation);

            if (order.from().equals(order.to()) == false) {
                updateAccountHistory(order.to(), operation);
            }
        }
    }

    public void foldTransaction(Receipt receipt) throws ReceiptException {
        if (receipt.isCancelled()) {
            throw ReceiptException.unableToFoldTransaction();
        }

        receipt.getOperation().reset();
        receipt.setCancelled(false);
    }

    private void updateAccountHistory(BankAccount account, AccountOperation operation) {
        account.addToHistory(new AccountOperationReceipt(
                operation.getTotal(),
                String.format("Operation was made with bank %s on %s.", this, LocalDateTime.now()),
                operation,
                operation.getOrders()));
    }

    public void calculateInterest() {
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

    public void calculateFee() {
        this.accounts.values().stream()
                .flatMap(Collection::stream)
                .filter(acc -> acc.account() instanceof CreditAccount && acc.account().getMoney() < 0)
                .forEach(acc -> acc.percentsAndFees().setFee(acc.percentsAndFees().getFee()
                        + Math.round((acc.account().getChargeRate() / 365 / 100) * Math.abs(acc.account().getMoney()) * 100.0) / 100.0));
    }

    public void accrueInterest() {
        this.accounts.values().stream()
                .flatMap(Collection::stream)
                .filter(acc -> !(acc.account() instanceof CreditAccount))
                .forEach(acc -> {
                    acc.account().setMoney(acc.account().getMoney() + acc.percentsAndFees().getPercent());
                    acc.percentsAndFees().setPercent(0);
                });
    }

    public void chargeFee() {
        this.accounts.values().stream()
                .flatMap(Collection::stream)
                .filter(acc -> acc.account() instanceof CreditAccount)
                .forEach(acc -> {
                    acc.account().setMoney(acc.account().getMoney() - acc.percentsAndFees().getFee());
                    acc.percentsAndFees().setFee(0);
                });
    }
}
