package org.banks.console.commands;

import org.banks.contracts.clients.ClientId;
import org.banks.controllers.BankController;
import org.banks.implementations.clients.RussianClientId;

import java.util.Scanner;

/**
 * {@inheritDoc}
 */
public class MakeDepositHandler extends CommandHandler{

    private final BankController controller;

    /**
     * Contructs "make deposit" handler instance
     * @param controller controller to handler requests
     */
    public MakeDepositHandler(BankController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (command.contains("make deposit")) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Specify your nationality");

            String clientCountry = scanner.nextLine().trim().toUpperCase();

            System.out.println("Specify your bank name.");

            String bankName = scanner.nextLine().trim();

            System.out.println("Specify your account type.");

            String accountType = scanner.nextLine().trim();

            System.out.println("Provide your ID number");

            int userId = Integer.parseInt(scanner.nextLine().trim());

            ClientId id = new RussianClientId(userId);

            System.out.println("Provide amount of money, you want to deposit");

            String total = scanner.nextLine().trim();

            try {
                System.out.println("Deposit " + controller.makeDeposit(id, bankName, accountType, total) + " was successfully made");
            } catch (Exception ex) {
                System.err.println("Transaction was failed");
                ex.printStackTrace();
            }
        } else {
            super.handle(command);
        }
    }
}
