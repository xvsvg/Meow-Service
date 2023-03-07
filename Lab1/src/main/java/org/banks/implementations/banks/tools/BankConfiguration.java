package org.banks.implementations.banks.tools;

import lombok.Getter;
import org.banks.exceptions.BankApplicationException;

public class BankConfiguration {

    @Getter
    private final String name;

    @Getter
    private final DepositAccountConfiguration configuration;

    @Getter
    private double commonInterestRate;

    @Getter
    private double commonChargeRate;

    public BankConfiguration(String name,
                             double commonInterestRate,
                             double commonChargeRate,
                             DepositAccountConfiguration configuration) {

        this.name = name;
        this.commonInterestRate = setCommonInterestRate(commonInterestRate);
        this.commonChargeRate = setCommonChargeRate(commonChargeRate);
        this.configuration = configuration;
    }

    public double setCommonInterestRate(double value) {
        if (value < 0)
            throw new BankApplicationException("Bank interest rate should be positive");

        this.commonInterestRate = value;

        return value;
    }

    public double setCommonChargeRate(double value) {
        if (value < 0)
            throw new BankApplicationException("Bank charge rate should be positive");

        this.commonChargeRate = value;

        return value;
    }
}
