package com.example;

import java.util.Objects;

import static java.text.Normalizer.normalize;

public class Category {
    private String name;

    private Category(String normalized) {
        this.name = normalized;
    }

    public static Category of(String rawName) {
        if (rawName == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        String trimmed = rawName.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }
        String normalized = normalize(trimmed, java.text.Normalizer.Form.NFKC);
        return new Category(normalized);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() { return name; }

}
