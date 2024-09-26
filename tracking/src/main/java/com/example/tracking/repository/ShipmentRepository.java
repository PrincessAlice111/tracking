package com.example.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tracking.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
