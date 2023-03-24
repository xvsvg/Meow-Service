package org.banks.console.commands;

import org.banks.contracts.accounts.BankAccount;
import org.banks.contracts.clients.ClientId;
import org.banks.controllers.BankController;
import org.banks.implementations.clients.RussianClientId;

import java.util.Scanner;

/**
 * {@inheritDoc}
 */
public class FindAccountHandler extends CommandHandler {
    private final BankController controller;

    /**
     * Constructs "find account" handler instance
     * @param controller controller to handler request
     */
    public FindAccountHandler(BankController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (command.contains("find")) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Specify your nationality:");

            String clientCountry = scanner.nextLine().toUpperCase();

            System.out.print("Specify your bank name: ");
            String bankName = scanner.nextLine();

            System.out.print("Specify your account type: ");
            String accountType = scanner.nextLine();

            System.out.print("Provide your ID number: ");
            int userId = scanner.nextInt();
            ClientId id = new RussianClientId(userId);

            try {
                BankAccount account = controller.findUserAccount(bankName, accountType, id);
                System.out.printf("Account of bank: %s\nMoney: %f\nAccount expires on: %s\nAccount is suspicious: %b\n",
                        account.getBank(), account.getMoney(), account.getExpirationDate(), account.getStatus());
            } catch (Exception ex) {
                System.err.println("User account was not found");
                System.err.println(ex.getMessage());
            }
        } else {
            super.handle(command);
        }
    }
}
