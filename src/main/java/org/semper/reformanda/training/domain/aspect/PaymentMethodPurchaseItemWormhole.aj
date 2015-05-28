package org.semper.reformanda.training.domain.aspect;


import org.apache.log4j.Logger;
import org.semper.reformanda.training.domain.Person;
import org.semper.reformanda.training.domain.PurchaseItem;
import org.semper.reformanda.training.domain.payment.PaymentMethod;

public aspect PaymentMethodPurchaseItemWormhole {
    private static final Logger log = Logger.getLogger(PaymentMethodPurchaseItemWormhole.class);

    public static pointcut purchaseCall(Person person, PurchaseItem purchaseItem)
            : execution(* org.semper.reformanda.training.domain.Person.purchase(PurchaseItem)) && this(person) && args(purchaseItem);

    public static pointcut payMethod(PaymentMethod paymentMethod)
            : execution(* org.semper.reformanda.training.domain.payment.PaymentMethod.pay(..)) && this(paymentMethod);

    public static pointcut payMethodInPurchaseCallWormhole(Person person, PurchaseItem purchaseItem, PaymentMethod paymentMethod)
            : cflow(purchaseCall(person, purchaseItem)) && payMethod(paymentMethod);

    //before(Person person, PurchaseItem purchaseItem, PaymentMethod paymentMethod) : payMethodInPurchaseCallWormhole(person, purchaseItem, paymentMethod) {
    //    log.info(String.format("%s is attempting to purchase %s for $%s with payment method %s", person.getName(), purchaseItem.getName(), purchaseItem.getCost(), paymentMethod.toString()));
    //}
}