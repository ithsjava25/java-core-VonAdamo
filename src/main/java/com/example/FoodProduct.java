package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable{
    private final LocalDate expirationDate;
    private final BigDecimal weight;

    public FoodProduct(UUID uuid, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(uuid, name, category, price);
        Objects.requireNonNull(expirationDate);
        Objects.requireNonNull(weight);
        if (weight.signum() < 0) throw new  IllegalArgumentException("Weight cannot be negative.");

        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    public String productDetails() {
        return "Food: " + name() + ", Expires: " + expirationDate.toString();
    }

    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    @Override
    public BigDecimal calculateShippingCost() {
        return weight.multiply(BigDecimal.valueOf(50))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public double weight() {
        return weight.doubleValue();
    }
}
