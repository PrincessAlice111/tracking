package com.example.tracking.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.tracking.model.Shipment;

@RestController
public class ShipmentController {
    private List<Shipment> shipments = new ArrayList<>();

    public ShipmentController() {
        // Create some statuses.
        Status status1 = new Status(1, "cancelled");
        Status status2 = new Status(2, "delivered");
        Status status3 = new Status(3, "pending");
        // Create dummy categories.
        Category category1 = new Category(1, "bird food");
        Category category2 = new Category(2, "cat food");
        Category category3 = new Category(3, "dog food");
        // Create dummy products.
        Product product1 = new Product(1, "seeds", category1);
        Product product2 = new Product(2, "cat kibble", category2);
        Product product3 = new Product(3, "dog kibble", category3);
        // Initialise the list of shipments with some data.
        shipments.add(new Shipment(
                1,
                Arrays.asList(
                        product1,
                        product1,
                        product1),
                status1));
        shipments.add(new Shipment(
                2,
                Arrays.asList(
                        product1,
                        product2,
                        product3),
                status2));
        shipments.add(new Shipment(
                3,
                Arrays.asList(
                        product1),
                status3));
    }

    @GetMapping("/shipments")
    public List<Shipment> getShipments() {
        return shipments;
    }

    @GetMapping("/shipments/{id}")
    public Shipment getShipmentById(@PathVariable int id) {
        for (Shipment shipment : shipments) {
            if (shipment.getId() == id) {
                return shipment;
            }
        }
        return null;
    }
}
