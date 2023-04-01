package org.banks;

import org.banks.console.BankCLI;

/**
 * Entrypoint to an application
 */
public class BankApplication {

    /**
     * Entrypoint to an application
     *
     * @param args entry args
     */
    public static void main(String[] args) {
        BankCLI cli = new BankCLI();
        cli.startInteractiveMode();
    }
}
