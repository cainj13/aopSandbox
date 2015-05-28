package org.semper.reformanda.training.domain;

import java.math.BigDecimal;

public class PurchaseItem {

    private final String name;
    private final BigDecimal cost;

    public PurchaseItem(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }
}