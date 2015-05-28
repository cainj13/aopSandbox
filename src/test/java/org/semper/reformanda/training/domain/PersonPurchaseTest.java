package org.semper.reformanda.training.domain;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.semper.reformanda.training.domain.payment.Cash;
import org.semper.reformanda.training.domain.payment.CreditCard;
import org.semper.reformanda.training.domain.wallet.RandomAccessWallet;
import org.semper.reformanda.training.domain.wallet.SequentialAccessWallet;
import org.semper.reformanda.training.domain.wallet.Wallet;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonPurchaseTest {
    private static final Logger log = Logger.getLogger(PersonPurchaseTest.class);

    @Test
    public void purchaseItemWhenLotsOfFundsAvailable() {
        log.info("\n\nPurchasing Item with lots of funds:\n");

        final Wallet wallet = new RandomAccessWallet(100)
                .addPaymentMethod(new Cash(new BigDecimal(7000)))
                .addPaymentMethod(new CreditCard("Platinum Visa", new BigDecimal(0), new BigDecimal(10000)))
                .addPaymentMethod(new CreditCard("Gold Mastercard", new BigDecimal(0), new BigDecimal(10000)))
                .addPaymentMethod(new CreditCard("American Express", new BigDecimal(0), new BigDecimal(10000)));

        final Person person = new Person("Ca$h Money", wallet);
        final boolean wasAbleToPurchase = person.purchase(new PurchaseItem("Used Motorcycle", new BigDecimal(5000)));
        assertTrue("Person was not able to purchase item, but sufficient funds were in wallet for purchase.", wasAbleToPurchase);
    }

    @Test
    public void purchaseItemWithRandomItemInWallet() {
        log.info("\n\nPurchasing Item with random card:\n");

        final Wallet wallet = new RandomAccessWallet(10)
                .addPaymentMethod(new Cash(new BigDecimal(200)))
                .addPaymentMethod(new CreditCard("Platinum Visa", new BigDecimal(10000), new BigDecimal(10000)))
                .addPaymentMethod(new CreditCard("Gold Mastercard", new BigDecimal(0), new BigDecimal(10000))) // sufficient funds
                .addPaymentMethod(new CreditCard("American Express", new BigDecimal(10000), new BigDecimal(10000)));

        final Person person = new Person("Ian Debt", wallet);
        final boolean wasAbleToPurchase = person.purchase(new PurchaseItem("Massive Television", new BigDecimal(5000)));
        assertTrue("Person was not able to purchase item, but sufficient funds were in wallet for purchase.", wasAbleToPurchase);
    }

    @Test
    public void purchaseItemWithLastItemInWallet() {
        log.info("\n\nPurchasing Item with last payment method in wallet:\n");

        final Wallet wallet = new SequentialAccessWallet()
                .addPaymentMethod(new Cash(new BigDecimal(200)))
                .addPaymentMethod(new CreditCard("Platinum Visa", new BigDecimal(0), new BigDecimal(1000)))
                .addPaymentMethod(new CreditCard("Gold Mastercard", new BigDecimal(0), new BigDecimal(1000)))
                .addPaymentMethod(new CreditCard("American Express", new BigDecimal(0), new BigDecimal(5000)));

        final Person person = new Person("No Credit", wallet);
        final boolean wasAbleToPurchase = person.purchase(new PurchaseItem("New Transmission", new BigDecimal(4000)));
        assertTrue("Person was not able to purchase item, but sufficient funds were in wallet for purchase.", wasAbleToPurchase);
    }


    @Test
    public void attemptToPurchaseWithInsufficientFunds() {
        log.info("\n\nPurchasing Item with last payment method in wallet:\n");

        final Wallet wallet = new SequentialAccessWallet()
                .addPaymentMethod(new Cash(new BigDecimal(200)))
                .addPaymentMethod(new CreditCard("Platinum Visa", new BigDecimal(3000), new BigDecimal(10000)))
                .addPaymentMethod(new CreditCard("Gold Mastercard", new BigDecimal(2000), new BigDecimal(10000)))
                .addPaymentMethod(new CreditCard("American Express", new BigDecimal(3000), new BigDecimal(3000)));

        final Person person = new Person("The Dreamer", wallet);
        final boolean wasAbleToPurchase = person.purchase(new PurchaseItem("Ferrari", new BigDecimal(250000)));
        assertFalse("Person was able to purchase item, but sufficient funds were NOT in wallet for purchase.", wasAbleToPurchase);
    }
}