package org.semper.reformanda.training.domain;

import org.semper.reformanda.training.domain.wallet.Wallet;

import java.util.Random;

public class Person {

    private final String name;
    private final Wallet wallet;

    public Person(String name, Wallet wallet) {
        this.name = name;
        this.wallet = wallet;
    }

    public String getName() {
        return name;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public boolean purchase(final PurchaseItem purchaseItem) {
        try {
            wallet.pay(purchaseItem.getCost());
            return true;
        } catch (NeedMoMoneyException e) {
            return false;
        }
    }
}