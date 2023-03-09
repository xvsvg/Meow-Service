package org.banks.console.commands;

import java.util.List;
import java.util.Scanner;

import org.banks.controllers.BankController;

/**
 * {@inheritDoc}
 */
public class CreateBankHandler extends CommandHandler {
    private final BankController controller;

    /**
     * Constructs "create bank" handler instance
     *
     * @param controller controller to handle request
     */
    public CreateBankHandler(BankController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (command.contains("create") && command.contains("bank")) {
            Scanner scanner = new Scanner(System.in);
            String name = promptWithMessage("Provide bank name", scanner);
            String commonInterestRate = promptWithMessage("Provide common interest rate", scanner);
            String commonChargeRate = promptWithMessage("Provide common charge rate", scanner);
            String deposits = promptWithMessage("Provide deposit bounds for deposit account, ex: 50000 100000 150000", scanner);
            String interests = promptWithMessage("Provide annual percent bounds for deposit account, ex: 3,5 4,5 5", scanner);

            try {
                String bankName = String.valueOf(controller.createBank(name, commonInterestRate, commonChargeRate, List.of(deposits.split(" ")), List.of(interests.split(" "))));
                System.out.printf("Bank \'%s\' was created.\n", bankName);
            } catch (Exception ex) {
                System.err.println("Bank creation failed");
                ex.printStackTrace();
            }
        } else {
            super.handle(command);
        }
    }

    private String promptWithMessage(String message, Scanner scanner) {
        System.out.print(message + ": ");
        return scanner.nextLine();
    }
}
