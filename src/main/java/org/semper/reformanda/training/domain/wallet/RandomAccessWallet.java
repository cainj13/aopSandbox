package org.semper.reformanda.training.domain.wallet;

import org.apache.log4j.Logger;
import org.semper.reformanda.training.domain.NeedMoMoneyException;
import org.semper.reformanda.training.domain.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAccessWallet implements Wallet {
    private static final Logger log = Logger.getLogger(RandomAccessWallet.class);

    private List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
    private final int maxNumberOfPaymentAttempts;

    public RandomAccessWallet(int maxNumberOfPaymentAttempts) {
        this.maxNumberOfPaymentAttempts = maxNumberOfPaymentAttempts;
    }

    public RandomAccessWallet addPaymentMethod(final PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
        return this;
    }

    @Override
    public void pay(final BigDecimal paymentAmount) throws NeedMoMoneyException {
        for (int i = 0; i < maxNumberOfPaymentAttempts; i++) {
            try {
                getRandomPaymentMethod().pay(paymentAmount);
                return;
            } catch (NeedMoMoneyException e) { // "use exceptions for exceptional conditions"?  Nah...
                log.debug("Not enough money for payment.");
            }
        }

        throw new NeedMoMoneyException("Could not satisfy payment request");
    }

    private PaymentMethod getRandomPaymentMethod() {
        Random random = new Random();
        return paymentMethods.get(random.nextInt(paymentMethods.size()));
    }
}