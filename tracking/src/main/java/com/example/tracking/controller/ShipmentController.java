package com.example.tracking.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.tracking.model.Shipment;
import com.example.tracking.service.ShipmentService;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    public Optional<Shipment> getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id);
    }

    @PostMapping
    public Shipment createShipment(@Validated @RequestBody Shipment shipment) {
        return shipmentService.createShipment(shipment);
    }

    @PutMapping("/{id}")
    public Shipment updateShipment(@PathVariable Long id, @Validated @RequestBody Shipment shipment) {
        return shipmentService.updateShipment(id, shipment);
    }

    @DeleteMapping("/{id}")
    public void deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
    }
}
