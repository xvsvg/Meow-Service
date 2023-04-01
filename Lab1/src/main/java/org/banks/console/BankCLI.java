package org.banks.console;

import org.banks.console.commands.*;
import org.banks.controllers.BankController;

/**
 * Represents CLI for application
 */
public class BankCLI {

    private final CommandHandler handler;
    private final BankController controller;

    /**
     * Constructs an instance of cli.
     */
    public BankCLI() {
        controller = new BankController();

        handler = new CreateCentralBankHandler(controller);

        handler
                .setNext(new CreateBankHandler(controller))
                .setNext(new CreateAccountHandler(controller))
                .setNext(new MakeTransactionHandler(controller))
                .setNext(new MakeDepositHandler(controller))
                .setNext(new MakeWithdrawHandler(controller))
                .setNext(new FindAccountHandler(controller));
    }

    /**
     * Executes conveyor of console interaction.
     */
    public void startInteractiveMode() {
        while (true) {
            String command = System.console().readLine();

            if (command.equals("clear"))
                clearScreen();
            else if (command.equals("exit"))
                return;
            else handler.handle(command);
        }
    }

    /**
     * Clears console
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
