package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class Warehouse {

    private static final Map<String, Warehouse> instances = new ConcurrentHashMap<>();
    private static final String DEFAULT_WAREHOUSE_NAME = "DefaultWarehouse";
    private final String name;
    private final List<Product> products = new ArrayList<>();
    private final Set<UUID> changed = new HashSet<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance() {
        return getInstance(DEFAULT_WAREHOUSE_NAME);
    }

    public static Warehouse getInstance(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Warehouse name cannot be null or blank.");
        }
        return instances.computeIfAbsent(name, Warehouse::new);
    }

    public String name() {
        return name;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (getProductById(product.uuid()).isPresent()) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        this.products.add(product);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }


    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.toUnmodifiableList()
                ));
    }

    public List<Perishable> expiredProducts() {
        return products.stream()
                // Filter only perishable products and check if they are expired
                // Using pattern matching for instanceof with a guard condition called 'per',
                // This way we avoid a second cast later,
                .filter(p -> p instanceof Perishable per && per.isExpired())
                // Cast to Perishable, since we know they are perishable from the filter step
                // Using a lambda to perform the cast
                .map(p -> (Perishable)p)
                // Collect to a list
                .toList();
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
    Product p = getProductById(id).orElseThrow(() ->
            new NoSuchElementException("Product not found with id: " + id));
            p.price(newPrice);
            changed.add(id);
    }

    public void remove(UUID id) {
        Product p = getProductById(id).orElseThrow(() ->
                new NoSuchElementException("Product not found with id: " + id));
            products.remove(p);
            changed.remove(id);
    }

    public List<Shippable> shippableProducts() {
        return products.stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .toList();
    }

    public Optional<Product> getProductById(UUID id) {
        return products.stream()
                // Find the product with the matching UUID
                .filter(p -> p.uuid()
                // Compare UUIDs using equals method
                .equals(id))
                // Return the first matching product wrapped in an Optional
                .findFirst();
    }

    public List<Product> getChangedProducts() {
        return products.stream()
                // Filter products whose UUIDs are in the 'changed' set
                .filter(p -> changed
                // Check if the set contains the product's UUID
                .contains(p.uuid()))
                // Collect the filtered products into a list
                .toList();
    }

    public void clearProducts() {
        products.clear();
        changed.clear();
    }

}
