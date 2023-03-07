package org.banks.implementations.banks.tools;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DepositAccountConfiguration {

    private final List<Double> interests;
    private final List<Double> deposits;

    public DepositAccountConfiguration(List<Double> interests, List<Double> deposits) {
        this.interests = interests;
        this.deposits = deposits;
    }

    public Collection<Double> getInterests() {
        return Collections.unmodifiableList(this.interests);
    }

    public Collection<Double> getDeposits() {
        return Collections.unmodifiableList(this.deposits);
    }
}
