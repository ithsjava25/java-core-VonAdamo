package com.example;

import java.math.BigDecimal;

public class ElectronicsProduct extends Product implements Shippable {
    int warrantyMonths;
    BigDecimal weight;

    public ElectronicsProduct(String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(name, category, price);
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    if (warranthyMonths < 0) {
        throw new IllegalArgumentException("Warranty months can't be negative");
    } else {
        this.warrantyMonths = warrantyMonths;
    }

    public String productDetails() {
        return "Electronic: " + name() + ", Warranty: " + warrantyMonths + " months";
    }
}
