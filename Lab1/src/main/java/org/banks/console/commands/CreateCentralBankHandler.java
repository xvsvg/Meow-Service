package org.banks.console.commands;

import org.banks.controllers.BankController;

import java.util.Scanner;

/**
 * {@inheritDoc}
 */
public class CreateCentralBankHandler extends CommandHandler {

    private final BankController controller;

    /**
     * Constructs "create central bank" handler instance
     * @param controller controller to handler request
     */
    public CreateCentralBankHandler(BankController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (command.contains("create") && command.contains("central bank")) {
            System.out.println("Provide local time");

            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();

            String prompt = "";
            if (answer.equals("Accelerated time")) {
                System.out.print("day duration and month duration in milliseconds? Ex: 1000 10000 ");
                prompt = scanner.nextLine();
            }

            System.out.print("provide bank capital: ");
            String capital = scanner.nextLine();

            try {
                String[] durations = prompt.split(" ");
                System.out.println(this.controller.createCentralBank(capital, durations[0], durations[1]) + " was successfully created.");
            } catch (Exception ex) {
                System.out.println("Bank creation failed");
                ex.printStackTrace();
            }
        } else {
            super.handle(command);
        }
    }
}
