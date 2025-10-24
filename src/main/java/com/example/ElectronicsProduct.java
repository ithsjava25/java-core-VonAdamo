package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {
    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID uuid, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(uuid, name, category, price);

        if (warrantyMonths < 0) throw new IllegalArgumentException("Warranty months cannot be negative.");
        Objects.requireNonNull(weight);
        if (weight.signum() < 0) throw new  IllegalArgumentException("Weight cannot be negative.");

        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }

    @Override
    public BigDecimal calculateShippingCost() {
        BigDecimal cost = BigDecimal.valueOf(79);

        if (weight.doubleValue() > 5.0) {
            cost = cost.add(BigDecimal.valueOf(49));
        }
        return cost.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public double weight() {
        return weight.doubleValue();
    }
}
