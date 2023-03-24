package org.banks.implementations.operations;

import org.banks.contracts.accounts.BankAccount;

/**
 * Represents order of accounts in which operation will be performed
 * @param from initiator of an operation
 * @param to receiver
 */
public record OperationOrder(BankAccount from, BankAccount to){}
