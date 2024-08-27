package com.example.tracking;

import java.util.List;

public class Shipment {
    private int id;
    private List<Product> products;
    private Status status;

    public Shipment(
            int id,
            List<Product> products,
            Status status) {
        this.id = id;
        this.products = products;
        this.status = status;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Status getStatus() {
        return status;
    }
}
