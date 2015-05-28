package org.semper.reformanda.training.domain.payment;

import org.semper.reformanda.training.domain.NeedMoMoneyException;

import java.math.BigDecimal;

public class Cash implements PaymentMethod {

    private BigDecimal amount;

    public Cash(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public void pay(final BigDecimal paymentAmount) throws NeedMoMoneyException {
        if (amount.compareTo(paymentAmount) < 0) {
            throw new NeedMoMoneyException("Not enough cash to fulfill payment request");
        } else {
            amount = amount.subtract(paymentAmount);
        }
    }

    @Override
    public String toString() {
        return "Cash{" +
                "amount=" + amount +
                '}';
    }
}