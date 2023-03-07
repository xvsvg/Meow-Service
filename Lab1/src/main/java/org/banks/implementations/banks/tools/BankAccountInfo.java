package org.banks.implementations.banks.tools;

import org.banks.contracts.accounts.BankAccount;

public record BankAccountInfo(PercentsAndFees percentsAndFees, BankAccount account) {}