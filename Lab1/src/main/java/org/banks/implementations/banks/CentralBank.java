package org.banks.implementations.banks;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.Getter;
import org.banks.contracts.clocks.Runnable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Represents central bank
 */
public class CentralBank {

    private final LinkedList<Bank> banks;

    /**
     * Returns daily event handler
     */
    @Getter
    private final Observer<Integer> dayChangeHandler;

    /**
     * Returns monthly event handler
     */
    @Getter
    private final Observer<Integer> monthChangeHandler;
    private final Runnable localTime;
    private final double centralBankMoney;

    /**
     * Constructs central bank instance
     * @param localTime clock for a bank
     * @param centralBankMoney initial money amount
     */
    public CentralBank(Runnable localTime, double centralBankMoney) {

        this.localTime = localTime;
        this.centralBankMoney = centralBankMoney;
        this.localTime.setBank(this);

        this.banks = new LinkedList<Bank>();

        dayChangeHandler = new Observer<Integer>() {

            @Override
            public void onNext(Integer tick) {
                dailyNotify();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
            }
        };

        monthChangeHandler = new Observer<Integer>() {

            @Override
            public void onNext(Integer tick) {
                monthlyNotify();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
            }
        };
    }

    /**
     * @return Bunch of registered banks
     */
    public Collection<Bank> getBanks() {
        return Collections.unmodifiableList(this.banks);
    }

    /**
     * Registers bank in central bank
     * @param bank bank to be registered
     * @return registered bank
     */
    public Bank addBank(Bank bank) {
        this.banks.add(bank);

        return bank;
    }

    /**
     * Removes bank from registered ones
     * @param bank bank to be removed
     */
    public void removeBank(Bank bank) {
        this.banks.remove(bank);
    }

    /**
     * Notifies banks about day change.
     * Banks must estimate fees and percents for all accounts
     */
    private void dailyNotify() {
        Observable<Bank> bankObservable;

        for (Bank bank : this.banks) {
            bankObservable = Observable.just(bank);
            bankObservable.subscribe(bank.getDayChangeHandler());
        }
    }

    /**
     * Notifies banks about month change.
     * Banks must apply estimated fees and percents for all accounts
     */
    private void monthlyNotify() {
        Observable<Bank> bankObservable;

        for (Bank bank : this.banks) {
            bankObservable = Observable.just(bank);
            bankObservable.subscribe(bank.getMonthChangeHandler());
        }
    }
}
