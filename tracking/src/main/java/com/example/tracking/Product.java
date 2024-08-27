package com.example.tracking;

public class Product {
    private int id;
    private String name;
    private Category category;

    public Product(
            int id,
            String name,
            Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
