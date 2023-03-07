package org.banks.implementations.operations;

import org.banks.contracts.accounts.BankAccount;

public record OperationOrder(BankAccount from, BankAccount to){}
