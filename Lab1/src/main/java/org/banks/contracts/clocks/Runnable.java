package org.banks.contracts.clocks;

import lombok.Setter;
import org.banks.implementations.banks.CentralBank;

/**
 * Represents interface for virtual clocks
 */
public interface Runnable {

    /**
     * Sets a bank for clock
     *
     * @param bank bank to be set clock for
     */
    public void setBank(CentralBank bank);

    /**
     * Starts clock
     */
    public void run();
}
