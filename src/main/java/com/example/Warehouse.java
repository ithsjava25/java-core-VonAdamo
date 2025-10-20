package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Warehouse {

    private static final Map<String, Warehouse> instances = new ConcurrentHashMap<>();
    private final String name;
    private static List<Product> products = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance(String name) {
        return instances.computeIfAbsent(name, Warehouse::new);
    }

    public boolean isEmpty() {
        return ();
    }

    public static void addProduct() {
        if (products == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public static void getProductsGroupedByCategories() {

    }

    public static void expiredProducts() {

    }

    public static void updateProductPrice() {

    }

    public void remove() {

    }

    public static void getProductById(UUID id) {
        return;
    }
}
