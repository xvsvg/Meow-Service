package org.banks.console.commands;

import jdk.jshell.spi.ExecutionControl;
import org.banks.controllers.BankController;
import org.jetbrains.annotations.Nullable;

/**
 * {@inheritDoc}
 */
public class CreateAccountHandler extends CommandHandler {

    private final BankController controller;

    /**
     * Constructs "create account" handler instance
     *
     * @param controller controller to be used to handle commands
     */
    public CreateAccountHandler(BankController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();
        String prompt;

        if (!(command.contains("create") && command.contains("account"))) {
            super.handle(command);
        }

        System.console().printf("Specify your nationality\n");
        prompt = System.console().readLine();

        if (!prompt.equals("RUS")) {
            throw new RuntimeException("Non russian accounts is not supported");
        }

        String name, accountType, money;
        @Nullable String expirationDate, overdraft;
        StringBuilder userData = new StringBuilder();

        System.console().printf("Specify bank name\n");
        name = System.console().readLine();

        System.console().printf("Specify your first name and second name\n");
        prompt = System.console().readLine();

        userData.append(prompt + " ");

        System.console().printf("Specify your address ( you can skip this step )\n");
        prompt = System.console().readLine();

        if (prompt.equals("skip")) {
            userData.append(prompt + " ");
        }

        System.console().printf("Specify your id ( you can skip this step )\n");
        prompt = System.console().readLine();

        if (prompt.equals("skip")) {
            userData.append(prompt + " ");
        }

        System.console().printf("Specify your account type\n");
        accountType = System.console().readLine();

        if (!accountType.equals("debit") && accountType != "credit" && accountType != "deposit") {
            throw new RuntimeException("provided account type doesn't exist");
        }

        System.console().printf("Specify initial money amount\n");
        money = System.console().readLine();

        if (!accountType.equals("deposit")) {
            System.console().printf("Specify account expiration date ( you can skip this step )\n");
            expirationDate = System.console().readLine();

            if (expirationDate.equals("skip")) {
                expirationDate = null;
            }
        } else {
            System.console().printf("Specify account expiration date\n");
            expirationDate = System.console().readLine();
        }

        if (!accountType.equals("credit")) {
            System.console().printf("Should we enable overdraft? True/False? ( you can skip this step ) \n");
            overdraft = System.console().readLine();

            if (overdraft.equals("skip"))
                overdraft = Boolean.FALSE.toString();
        } else {
            overdraft = Boolean.TRUE.toString();
        }

        try {
            System.console().format("%s was created", controller.createRussianBankAccount(name,
                    userData.toString(),
                    accountType,
                    money,
                    expirationDate,
                    overdraft));
        } catch (RuntimeException e) {
            System.console().format("Bank account %s creation was failed\n %s", controller.createRussianBankAccount(name,
                            userData.toString(),
                            accountType,
                            money,
                            expirationDate,
                            overdraft),
                    e.getMessage());
        }
    }
}
