package org.semper.reformanda.training.domain.wallet;

import org.apache.log4j.Logger;
import org.semper.reformanda.training.domain.NeedMoMoneyException;
import org.semper.reformanda.training.domain.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SequentialAccessWallet implements Wallet {
    private static final Logger log = Logger.getLogger(SequentialAccessWallet.class);


    private List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

    public SequentialAccessWallet addPaymentMethod(final PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
        return this;
    }

    @Override
    public void pay(BigDecimal paymentAmount) throws NeedMoMoneyException {
        for (PaymentMethod paymentMethod : paymentMethods) {
            try {
                paymentMethod.pay(paymentAmount);
                return;
            } catch (NeedMoMoneyException e) { // "use exceptions for exceptional conditions"?  Nah...
                log.debug("Not enough money for payment.");
            }
        }

        throw new NeedMoMoneyException("Could not satisfy payment request");
    }
}
