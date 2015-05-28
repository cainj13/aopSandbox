package org.semper.reformanda.training.domain.payment;

import org.semper.reformanda.training.domain.NeedMoMoneyException;

import java.math.BigDecimal;

public interface PaymentMethod {

    public void pay(BigDecimal paymentAmount) throws NeedMoMoneyException;
}