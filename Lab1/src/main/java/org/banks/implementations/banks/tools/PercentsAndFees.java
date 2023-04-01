package org.banks.implementations.banks.tools;

import lombok.Getter;
import org.banks.exceptions.BankAccountException;

/**
 * Represents value object for percents and fees
 * for some bank
 */
public class PercentsAndFees {

    /**
     * Returns common interest rate for bank
     */
    @Getter
    private double percent;

    /**
     * Returns common charge rate for bank
     */
    @Getter
    private double fee;

    /**
     * Constructs new instance
     * @param percent percent amount in format 3,65, that means 3,65% annual income
     * @param fee fee amount in format 3,65, that means 3,65% annual charges
     */
    public PercentsAndFees(double percent, double fee) {
        this.percent = setPercent(percent);
        this.fee = setFee(fee);
    }

    /**
     * Sets interest rate in format: 3,65,
     * that means that 3,65% annual income
     *
     * @param value interest rate to be set
     * @return validated interest rate
     */
    public double setPercent(double value) {
        if (value < 0)
            throw BankAccountException.NegativeRateAmountException("percent cannot be negative");

        this.percent = value;

        return value;
    }

    /**
     * Sets charge rate in format: 3,65,
     * that means that 3,65% annual charges
     *
     * @param value charge rate to be set
     * @return validated charge rate
     */
    public double setFee(double value) throws BankAccountException {
        if (value < 0)
            throw BankAccountException.NegativeChargesAmountException("fees cannot be negative");

        this.percent = value;

        return value;
    }
}
