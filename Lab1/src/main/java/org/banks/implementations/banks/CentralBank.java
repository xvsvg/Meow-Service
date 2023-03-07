package org.banks.implementations.banks;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.Getter;
import org.banks.contracts.clocks.Runnable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class CentralBank {

    private final LinkedList<Bank> banks;

    @Getter
    private final Observer<Integer> dayChangeHandler;

    @Getter
    private final Observer<Integer> monthChangeHandler;
    private final Runnable localTime;
    private final double centralBankMoney;

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

    public Collection<Bank> getBanks() {
        return Collections.unmodifiableList(this.banks);
    }

    public Bank addBank(Bank bank) {
        this.banks.add(bank);

        return bank;
    }

    public void removeBank(Bank bank) {
        this.banks.remove(bank);
    }

    private void dailyNotify() {
        Observable<Bank> bankObservable;

        for (Bank bank : this.banks) {
            bankObservable = Observable.just(bank);
            bankObservable.subscribe(bank.getDayChangeHandler());
        }
    }

    private void monthlyNotify() {
        Observable<Bank> bankObservable;

        for (Bank bank : this.banks) {
            bankObservable = Observable.just(bank);
            bankObservable.subscribe(bank.getMonthChangeHandler());
        }
    }
}
