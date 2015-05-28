package org.semper.reformanda.training.domain.aspect;


import org.apache.log4j.Logger;
import org.semper.reformanda.training.domain.NeedMoMoneyException;
import org.semper.reformanda.training.domain.payment.CreditCard;

public aspect CreditCardMaxedOutWarning {
    private static final Logger log = Logger.getLogger(CreditCardMaxedOutWarning.class);

    public static pointcut creditCardPayMethod(CreditCard creditCard)
            : execution(* org.semper.reformanda.training.domain.payment.CreditCard.pay(..)) && this(creditCard);

    //after(CreditCard creditCard) throwing(NeedMoMoneyException exception) : creditCardPayMethod(creditCard) {
    //    if (creditCard.getBalance().equals(creditCard.getCreditLimit())) {
    //        log.warn(String.format("********************** WARNING - Your credit card: %s Has been maxed out! *************************", creditCard.getCardName()));
    //    }
    //}
}
