package org.banks.console.commands;

import org.banks.contracts.clients.ClientId;
import org.banks.controllers.BankController;
import org.banks.implementations.clients.RussianClientId;

import java.util.Scanner;

/**
 * {@inheritDoc}
 */
public class MakeTransactionHandler extends CommandHandler {

    private final BankController controller;
    private final Scanner scanner;

    /**
     * Constructs "make transaction" handler instance
     * @param controller controller to handler request
     */
    public MakeTransactionHandler(BankController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (command.contains("make transaction")) {
            System.out.print("Provide your bank name: ");
            String clientBankName = scanner.nextLine();

            System.out.println("Specify your nationality");

            String clientCountry = scanner.nextLine();

            System.out.print("Your ID, please: ");
            int id = Integer.parseInt(scanner.nextLine());
            ClientId client = new RussianClientId(id);

            System.out.println("Specify desired account type");

            String clientAccountType = scanner.nextLine();

            System.out.print("Provide recipient's bank name: ");
            String recipientBankName = scanner.nextLine();

            System.out.println("Specify recipient's nationality");

            String recipientCountry = scanner.nextLine();

            System.out.print("Recipient ID, please: ");
            id = Integer.parseInt(scanner.nextLine());
            ClientId recipient = new RussianClientId(id);

            System.out.println("Specify desired account type");
            String recepientAccountType = scanner.nextLine();

            System.out.print("Recipient ID, please: ");
            String total = scanner.nextLine();

            controller.makeTransaction(clientBankName,
                    client,
                    clientAccountType,
                    recipientBankName,
                    recipient,
                    recepientAccountType,
                    total);
        } else {
            super.handle(command);
        }
    }
}
