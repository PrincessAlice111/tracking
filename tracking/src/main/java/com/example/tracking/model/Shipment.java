package com.example.tracking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Product is mandatory.")
    @Column(name = "product")
    private String product;

    @NotBlank(message = "Status is mandatory.")
    @Pattern(regexp = "^(cancelled|delivered|pending)$", message = "Status must be either \"cancelled\", \"delivered\" or \"pending\".")
    @Column(name = "status")
    private String status;

    @NotBlank(message = "Address is mandatory.")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Recipient is mandatory.")
    @Column(name = "recipient")
    private String recipient;

    // Getters.
    public Long getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public String getRecipient() {
        return recipient;
    }

    // Setters.
    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
