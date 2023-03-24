package org.banks.implementations.clocks;

import io.reactivex.rxjava3.core.Observable;
import org.banks.implementations.banks.CentralBank;
import org.banks.contracts.clocks.Runnable;
import org.jetbrains.annotations.Nullable;

/**
 * {@inheritDoc}
 */
public class IntegerClock implements Runnable {

    private int timeOffset;

    @Nullable
    private CentralBank bank;
    private final int timeToWait;
    private final int notifyFrequency;

    /**
     * Constructs integer clock instance
     * @param bank bank, that clock created for
     * @param timeBetweenTicksInMilleseconds time between ticks in milleseconds
     * @param notifyFrequency time between months in milleseconds
     */
    public IntegerClock(@Nullable CentralBank bank, int timeBetweenTicksInMilleseconds, int notifyFrequency) {
        this.bank = bank;
        this.timeToWait = timeBetweenTicksInMilleseconds;
        this.notifyFrequency = notifyFrequency;
        this.timeOffset = 0;
    }

    @Override
    public void run() {
        while (true) {
            ++timeOffset;
            Observable<Integer> notifier = Observable.just(timeOffset);
            notifier.subscribe(bank.getDayChangeHandler());

            if (timeOffset % notifyFrequency == 0) {
                notifier.subscribe(bank.getMonthChangeHandler());
            }
        }
    }

    @Override
    public void setBank(CentralBank bank) {
        this.bank = bank;
    }
}
