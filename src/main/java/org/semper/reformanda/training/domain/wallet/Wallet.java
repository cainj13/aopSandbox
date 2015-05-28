package org.semper.reformanda.training.domain.wallet;

import org.semper.reformanda.training.domain.NeedMoMoneyException;

import java.math.BigDecimal;

public interface Wallet {

    public void pay(final BigDecimal paymentAmount) throws NeedMoMoneyException;
}