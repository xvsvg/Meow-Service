package org.banks.implementations.banks.tools;

import lombok.Getter;
import org.banks.exceptions.BankAccountException;

public class PercentsAndFees {

    @Getter
    private double percent;

    @Getter
    private double fee;

    public PercentsAndFees(double percent, double fee) {
        this.percent = percent;
        this.fee = fee;
    }

    public double setPercent(double value){
        if (value < 0)
            throw BankAccountException.NegativeRateAmountException("percent cannot be negative");

        this.percent = value;

        return value;
    }

    public double setFee(double value) throws BankAccountException {
        if (value < 0)
            throw BankAccountException.NegativeChargesAmountException("fees cannot be negative");

        this.percent = value;

        return value;
    }
}
