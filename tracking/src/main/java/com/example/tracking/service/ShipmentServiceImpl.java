package com.example.tracking.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tracking.model.Shipment;
import com.example.tracking.repository.ShipmentRepository;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @Override
    public Optional<Shipment> getShipmentById(Long id) {
        return shipmentRepository.findById(id);
    }

    @Override
    public Shipment createShipment(Shipment student) {
        return shipmentRepository.save(student);
    }

    @Override
    public Shipment updateShipment(Long id, Shipment student) {
        if (shipmentRepository.existsById(id)) {
            student.setId(id);
            return shipmentRepository.save(student);
        } else {
            return null;
        }
    }

    @Override
    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }
}
