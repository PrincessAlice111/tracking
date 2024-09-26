package com.example.tracking.service;

import java.util.List;
import java.util.Optional;
import com.example.tracking.model.Shipment;

public interface ShipmentService {
    List<Shipment> getAllShipments();

    Optional<Shipment> getShipmentById(Long id);

    Shipment createShipment(Shipment shipment);

    Shipment updateShipment(Long id, Shipment shipment);

    void deleteShipment(Long id);
}
