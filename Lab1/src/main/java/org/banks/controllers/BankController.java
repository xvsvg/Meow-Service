package org.banks.controllers;

import org.banks.contracts.accounts.BankAccount;
import org.banks.contracts.clients.Client;
import org.banks.contracts.clients.ClientId;
import org.banks.contracts.receipts.Receipt;
import org.banks.implementations.accounts.CreditAccount;
import org.banks.implementations.accounts.DebitAccount;
import org.banks.implementations.accounts.DepositAccount;
import org.banks.implementations.banks.*;
import org.banks.implementations.banks.tools.BankAccountInfo;
import org.banks.implementations.banks.tools.BankConfiguration;
import org.banks.implementations.banks.tools.DepositAccountConfiguration;
import org.banks.implementations.clients.RussianClient;
import org.banks.implementations.clients.RussianClientBuilder;
import org.banks.implementations.clients.RussianClientId;
import org.banks.implementations.clocks.IntegerClock;
import org.banks.implementations.operations.MakeDeposit;
import org.banks.implementations.operations.MakeTransaction;
import org.banks.implementations.operations.OperationOrder;
import org.banks.implementations.operations.Withdraw;
import org.jetbrains.annotations.Nullable;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents facade over all domain operations.
 */
public class BankController {

    @Nullable
    private CentralBank centralBank;

    /**
     * Creates central bank in application
     *
     * @param capital       initial money amount
     * @param dayDuration   day duration in milliseconds
     * @param monthDuration month duration in milliseconds
     * @return new instance of central bank
     */
    public CentralBank createCentralBank(String capital,
                                         String dayDuration,
                                         String monthDuration) {

        IntegerClock clock = new IntegerClock(null, Integer.parseInt(dayDuration), Integer.parseInt(monthDuration));
        centralBank = new CentralBank(clock, Double.parseDouble(capital));

        return centralBank;
    }


    /**
     * Registers new bank in a central bank
     *
     * @param bankName  bank name
     * @param interest  common interest rate for a bank
     * @param charge    common charge rate for a bank
     * @param deposits  money bounds for a deposit accounts
     * @param interests corresponding to deposits interest rate
     * @return new instance of a bank
     * @throws RuntimeException iff central bank wasn't created
     */
    public Bank createBank(String bankName,
                           String interest,
                           String charge,
                           Collection<String> deposits,
                           Collection<String> interests) {
        if (centralBank == null) {
            throw new RuntimeException("Create central bank first.");
        }

        var deps = new LinkedList<Double>();
        var ints = new LinkedList<Double>();

        deposits.forEach(deposit -> deps.add(Double.parseDouble(deposit)));
        interests.forEach(interestRate -> deps.add(Double.parseDouble(interestRate)));

        return centralBank.addBank(new Bank(centralBank, new BankConfiguration(
                bankName,
                Double.parseDouble(interest),
                Double.parseDouble(charge),
                new DepositAccountConfiguration(
                        deps,
                        ints
                )
        )));
    }

    /**
     * Creates russian bank account.
     *
     * @param name           bank name in which account will be registered
     * @param userData       first name, second name, address, id
     * @param accountType    deposit, credit, debit
     * @param money          initial money amount
     * @param expirationDate account expiration date
     * @param overdraft      {@code false} means disabled overdraft.
     * @return new instance of bank account
     * @throws RuntimeException <div>
     *                                                                                                                                                                                                                                  <ul>
     *                                                                                                                                                                                                                                  <li>if central bank wasn't created</li>
     *                                                                                                                                                                                                                                  <li>if desired bank doesn't exist</li>
     *                                                                                                                                                                                                                                  <li>if desired account type doesn't exist</li>
     *                                                                                                                                                                                                                                  </ul>
     *                                                                                                                                                                                                                                  </div>
     */
    public BankAccount createRussianBankAccount(String name,
                                                String userData,
                                                String accountType,
                                                String money,
                                                @Nullable String expirationDate,
                                                @Nullable String overdraft) {

        if (centralBank == null) {
            throw new RuntimeException("Create central bank first.");
        }

        @Nullable Bank bank = centralBank.getBanks().stream()
                .filter(b -> b.toString().equals(name))
                .findFirst()
                .orElse(null);

        if (bank == null) {
            throw new RuntimeException("Bank with specified name does not exist.");
        }

        RussianClient client = createRussianClient(userData);

        @Nullable BankAccount account = null;

        if (accountType.equals("credit")) {
            account = createCreditAccount(bank, client, money, expirationDate);
        }

        if (accountType.equals("deposit")) {
            account = createDepositAccount(bank, client, money, overdraft, expirationDate);
        }

        if (accountType.equals("debit")) {
            account = createDebitAccount(bank, client, money, overdraft, expirationDate);
        }

        if (account == null) {
            throw new RuntimeException("Specified account type is not supported.");
        }

        return bank.addBankAccount(account);
    }

    /**
     * Performs transaction between accounts
     *
     * @param clientBankName       name of initiators bank
     * @param id                   id of initiator
     * @param accountType          type of initiators bank account
     * @param recepientBankName    name of recepients bank
     * @param recepient            id of recepient
     * @param recepientAccountType recepient account type
     * @param total                total money amount to be transacted
     * @return new instance of receipt
     * @throws RuntimeException <div>
     *                                                                                                                                                                                                                                  <ul>
     *                                                                                                                                                                                                                                  <li>if central bank wasn't created</li>
     *                                                                                                                                                                                                                                  <li>if users banks weren't found</li>
     *                                                                                                                                                                                                                                  <li>if users don't exist</li>
     *                                                                                                                                                                                                                                  </ul>
     *                                                                                                                                                                                                                                  </div>
     */
    public Receipt makeTransaction(String clientBankName,
                                   ClientId id,
                                   String accountType,
                                   String recepientBankName,
                                   ClientId recepient,
                                   String recepientAccountType,
                                   String total) {

        if (centralBank == null) {
            throw new RuntimeException("Create central bank first.");
        }

        @Nullable Bank clientBank = centralBank.getBanks().stream()
                .filter(b -> b.toString().equals(clientBankName))
                .findFirst()
                .orElse(null);

        @Nullable Bank recepientBank = centralBank.getBanks().stream()
                .filter(b -> b.toString().equals(recepientBankName))
                .findFirst()
                .orElse(null);

        if (clientBank == null) {
            throw new RuntimeException("Specified clients' bank does not exist.");
        }

        if (recepientBank == null) {
            throw new RuntimeException("Specified recepients' bank does not exist.");
        }

        @Nullable BankAccount clientAccount = clientBank.findBankAccount(id).stream()
                .filter(a -> a.account().getClass().toString().equals(accountType))
                .map(BankAccountInfo::account)
                .findFirst()
                .orElse(null);

        @Nullable BankAccount recepientAccount = clientBank.findBankAccount(id).stream()
                .filter(a -> a.account().getClass().toString().equals(recepientAccountType))
                .map(BankAccountInfo::account)
                .findFirst()
                .orElse(null);

        if (clientAccount == null || recepientAccount == null)
            throw new RuntimeException("Some users hadn't found");

        clientBank.makeTransaction(new MakeTransaction(
                List.of(new OperationOrder(clientAccount, recepientAccount)),
                Double.parseDouble(total)));

        int index = clientAccount.getHistory().size();
        return clientAccount.getHistory().stream().toList().get(index - 1);
    }

    /**
     * Performs replenishment operation.
     *
     * @param id          client id
     * @param bankName    name of bank, where account is registered
     * @param accountType type of account
     * @param total       amount of deposit
     * @return new receipt instance
     * @throws RuntimeException <div>
     *                                                                                                                                                       <ul>
     *                                                                                                                                                           <li>if central bank doesn't exist</li>
     *                                                                                                                                                           <li>if clients bank doesn't exist</li>
     *                                                                                                                                                           <li>if user not found</li>
     *                                                                                                                                                       </ul>
     *                                                                                                                                                       </div>
     */
    public Receipt makeDeposit(ClientId id,
                               String bankName,
                               String accountType,
                               String total) {

        if (centralBank == null) {
            throw new RuntimeException("Create central bank first.");
        }

        @Nullable Bank bank = centralBank.getBanks().stream()
                .filter(b -> b.toString().equals(bankName))
                .findFirst()
                .orElse(null);

        if (bank == null) {
            throw new RuntimeException("Specified clients' bank does not exist.");
        }

        @Nullable BankAccount account = bank.findBankAccount(id).stream()
                .filter(a -> a.account().getClass().toString().equals(accountType))
                .map(BankAccountInfo::account)
                .findFirst()
                .orElse(null);

        if (account == null) {
            throw new RuntimeException("user not found");
        }

        bank.makeTransaction(new MakeDeposit(account, Double.parseDouble(total)));

        int index = account.getHistory().size();
        return account.getHistory().stream().toList().get(index - 1);
    }

    /**
     * Performs write-off operation.
     *
     * @param id          client id
     * @param bankName    name of bank, where account is registered
     * @param accountType type of account
     * @param total       amount of deposit
     * @return new receipt instance
     * @throws RuntimeException <div>
     *                                                                                                                                                       <ul>
     *                                                                                                                                                           <li>if central bank doesn't exist</li>
     *                                                                                                                                                           <li>if clients bank doesn't exist</li>
     *                                                                                                                                                           <li>if user not found</li>
     *                                                                                                                                                       </ul>
     *                                                                                                                                                       </div>
     */
    public Receipt makeWithdraw(ClientId id,
                                String bankName,
                                String accountType,
                                String total) {

        if (centralBank == null) {
            throw new RuntimeException("Create central bank first.");
        }

        @Nullable Bank bank = centralBank.getBanks().stream()
                .filter(b -> b.toString().equals(bankName))
                .findFirst()
                .orElse(null);

        if (bank == null) {
            throw new RuntimeException("Specified clients' bank does not exist.");
        }

        @Nullable BankAccount account = bank.findBankAccount(id).stream()
                .filter(a -> a.account().getClass().toString().equals(accountType))
                .map(BankAccountInfo::account)
                .findFirst()
                .orElse(null);

        if (account == null) {
            throw new RuntimeException("user not found");
        }

        bank.makeTransaction(new Withdraw(account, Double.parseDouble(total)));

        int index = account.getHistory().size();
        return account.getHistory().stream().toList().get(index - 1);
    }

    /**
     * Finds specified user account
     *
     * @param bankName    user bank account
     * @param accountType user account type
     * @param id          client id
     * @return users bank account
     * @throws RuntimeException <div>
     *                                                                                                     <ul>
     *                                                                                                         <li>if central bank wasn't created</li>
     *                                                                                                         <li>if bank wasn't found</li>
     *                                                                                                         <li>if account of provided type wasn't found</li>
     *                                                                                                     </ul>
     *                                                                                                     </div>
     */
    public BankAccount findUserAccount(String bankName,
                                       String accountType,
                                       ClientId id) {

        if (centralBank == null) {
            throw new RuntimeException("Create central bank first.");
        }

        @Nullable Bank bank = centralBank.getBanks().stream()
                .filter(b -> b.toString().equals(bankName))
                .findFirst()
                .orElse(null);

        if (bank == null) {
            throw new RuntimeException("Bank not found");
        }

        @Nullable BankAccount account = bank.findBankAccount(id).stream()
                .filter(a -> a.account().getClass().toString().contains(accountType))
                .map(BankAccountInfo::account)
                .findFirst()
                .orElse(null);

        if (account == null) {
            throw new RuntimeException("User not found");
        }

        return account;
    }

    private RussianClient createRussianClient(String userData) {
        var builder = new RussianClientBuilder();
        builder.setFirstName(userData.split(" ")[0])
                .setSecondName(userData.split(" ")[1]);

        if (userData.split(" ").length > 2) {
            builder.setAddress(userData.split(" ")[2]);
            builder.setAddress(new RussianClientId(Integer.parseInt(userData.split(" ")[3])).toString());
        }
        return builder.createRussianClient();
    }

    private BankAccount createCreditAccount(Bank bank,
                                            Client client,
                                            String money,
                                            @Nullable String expirationDate) {

        if (expirationDate != null) {
            return new CreditAccount(bank,
                    client,
                    Double.parseDouble(money),
                    LocalTime.parse(expirationDate));
        } else return new CreditAccount(bank,
                client,
                Double.parseDouble(money),
                null);
    }

    private BankAccount createDepositAccount(Bank bank,
                                             Client client,
                                             String money,
                                             @Nullable String overdraft,
                                             @Nullable String expirationDate) {

        return new DepositAccount(bank,
                client,
                Double.parseDouble(money),
                Boolean.parseBoolean(overdraft),
                LocalTime.parse(expirationDate));
    }

    private BankAccount createDebitAccount(Bank bank,
                                           Client client,
                                           String money,
                                           @Nullable String overdraft,
                                           @Nullable String expirationDate) {

        if (expirationDate != null) {
            return new DebitAccount(bank,
                    client,
                    Double.parseDouble(money),
                    Boolean.parseBoolean(overdraft),
                    LocalTime.parse(expirationDate));
        } else return new DebitAccount(bank,
                client,
                Double.parseDouble(money),
                Boolean.parseBoolean(overdraft),
                null);
    }
}
