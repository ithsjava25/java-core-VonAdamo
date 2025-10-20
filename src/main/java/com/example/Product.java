package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public abstract class Product {
    private UUID uuid;
    private String name;
    private Category category;
    private BigDecimal price;

    public Product(String name, Category category, BigDecimal price) {
        this.uuid = UUID.randomUUID();
        this.name = name;
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

    public void setPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public abstract String productDetails();
}
