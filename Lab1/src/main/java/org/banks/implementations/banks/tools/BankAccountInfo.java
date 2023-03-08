package org.banks.implementations.banks.tools;

import org.banks.contracts.accounts.BankAccount;

/**
 * Represents immutable account information
 * @param percentsAndFees percents and fees that applied to an account
 * @param account account to which information will be applied
 */
public record BankAccountInfo(PercentsAndFees percentsAndFees, BankAccount account) {}