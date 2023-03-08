package org.banks.implementations.banks.tools;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents deposit account configuration
 */
public class DepositAccountConfiguration {

    private final List<Double> interests;
    private final List<Double> deposits;

    /**
     * Constructs deposit account configuration instance
     * @param interests interest rate bounds
     * @param deposits money bounds on which interest rate depends
     */
    public DepositAccountConfiguration(List<Double> interests, List<Double> deposits) {
        this.interests = interests;
        this.deposits = deposits;
    }

    /**
     * @return Money bounds for certain interests
     */
    public Collection<Double> getInterests() {
        return Collections.unmodifiableList(this.interests);
    }

    /**
     * @return Money bounds for certain deposits
     */
    public Collection<Double> getDeposits() {
        return Collections.unmodifiableList(this.deposits);
    }
}
