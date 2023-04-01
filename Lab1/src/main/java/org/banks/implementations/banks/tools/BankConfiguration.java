package org.banks.implementations.banks.tools;

import lombok.Getter;
import org.banks.exceptions.BankApplicationException;

/**
 * Represents bank configuration
 */
public class BankConfiguration {

    /**
     * @return Name of bank
     */
    @Getter
    private final String name;

    /**
     * Returns configuration of deposit accounts registered
     * in a bank
     * @see org.banks.implementations.banks.tools.DepositAccountConfiguration
     */
    @Getter
    private final DepositAccountConfiguration configuration;

    /**
     * Returns common interest rate for a bank
     */
    @Getter
    private double commonInterestRate;

    /**
     * Return common charge rate for a bank
     */
    @Getter
    private double commonChargeRate;

    /**
     * Constructs bank configuration instance
     * @param name name of a bank
     * @param commonInterestRate common interest rate for a bank
     * @param commonChargeRate common charge rate for a bank
     * @param configuration configuration for all deposit accounts registered in a bank
     */
    public BankConfiguration(String name,
                             double commonInterestRate,
                             double commonChargeRate,
                             DepositAccountConfiguration configuration) {

        this.name = name;
        this.commonInterestRate = setCommonInterestRate(commonInterestRate);
        this.commonChargeRate = setCommonChargeRate(commonChargeRate);
        this.configuration = configuration;
    }

    /**
     * Sets common interest rate for a bank
     * @param value value to be set
     * @return validated set value
     */
    public double setCommonInterestRate(double value) {
        if (value < 0)
            throw new BankApplicationException("Bank interest rate should be positive");

        this.commonInterestRate = value;

        return value;
    }

    /**
     * Sets common charge rate for a bank
     * @param value value to be set
     * @return validated set value
     */
    public double setCommonChargeRate(double value) {
        if (value < 0)
            throw new BankApplicationException("Bank charge rate should be positive");

        this.commonChargeRate = value;

        return value;
    }
}
