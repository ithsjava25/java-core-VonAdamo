package com.example;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Objects;

public abstract class Product {
    private final UUID uuid;
    private final String name;
    private final Category category;
    private BigDecimal price;

    public Product(UUID uuid, String name, Category category, BigDecimal price) {
        Objects.requireNonNull(uuid);
        Objects.requireNonNull(name);
        Objects.requireNonNull(category);
        Objects.requireNonNull(price);
        if (price.signum() < 0) throw new  IllegalArgumentException("Price cannot be negative.");

        this.uuid = uuid;
        this.name = name.trim();
        this.category = category;
        this.price = price;
    }

    public UUID uuid() {
        return uuid;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    public BigDecimal price() {
        return price;
    }

    public void price(BigDecimal newPrice) {
        Objects.requireNonNull(newPrice);
        this.price = newPrice;
    }

    public abstract String productDetails();
}
