package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FoodProduct extends Product implements Perishable, Shippable{
    LocalDate expirationDate;
    BigDecimal weight;

    public FoodProduct(String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(name, category, price);
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

        return BigDecimal.ZERO;
    }

    @Override
    public double weight() {
        return weight.doubleValue();
    }
}
