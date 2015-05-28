package org.semper.reformanda.training.domain.payment;

import org.semper.reformanda.training.domain.NeedMoMoneyException;

import java.math.BigDecimal;

public class CreditCard implements PaymentMethod {

    private String cardName;
    private BigDecimal balance;
    private BigDecimal creditLimit;

    public CreditCard(String cardName, BigDecimal balance, BigDecimal creditLimit) {
        this.cardName = cardName;
        this.balance = balance;
        this.creditLimit = creditLimit;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public void pay(final BigDecimal paymentAmount) throws NeedMoMoneyException {
        if (creditLimit.subtract(balance.add(paymentAmount)).compareTo(BigDecimal.ZERO) < 0) {
            throw new NeedMoMoneyException("Credit card rejected - insufficient funds");
        } else {
            balance = balance.add(paymentAmount);
        }
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardName='" + cardName + '\'' +
                ", balance=" + balance +
                ", creditLimit=" + creditLimit +
                '}';
    }
}