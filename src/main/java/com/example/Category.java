package com.example;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Category {
    private static final ConcurrentMap<String, Category> CACHE = new ConcurrentHashMap<>();
    private final String name;

    private Category(String normalized) {
        this.name = normalized;
    }

    public static Category of(String rawName) {
        if (rawName == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        String trimmed = rawName.trim();
        if (trimmed.isBlank()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }
        String normalized = normalizeName(trimmed);
        return CACHE.computeIfAbsent(normalized, Category::new);
    }

    private static String normalizeName(String s) {
        if (s.length() == 1) return s.substring(0, 1).toUpperCase(Locale.ROOT);
        String lower = s.toLowerCase(Locale.ROOT);
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category other)) return false;
        return java.util.Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() { return name; }

}
