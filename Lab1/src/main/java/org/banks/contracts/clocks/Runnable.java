package org.banks.contracts.clocks;

import lombok.Setter;
import org.banks.implementations.banks.CentralBank;

public interface Runnable {

    public void setBank(CentralBank bank);
    public void run();
}
