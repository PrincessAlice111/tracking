package com.example.tracking.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        ShipmentController controller = methodOn(ShipmentController.class);
        List<EntityModel<Shipment>> shipmentList = shipments.stream().map(shipment -> {
            EntityModel<Shipment> model = EntityModel.of(shipment);
            model.add(linkTo(controller.getShipmentById(shipment.getId())).withSelfRel());
            return model;
        }).collect(Collectors.toList());
        CollectionModel<EntityModel<Shipment>> model = CollectionModel.of(shipmentList);
        model.add(linkTo(controller.getAllShipments()).withSelfRel());
        return ResponseEntity.ok(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getShipmentById(@PathVariable Long id) {
        Optional<Shipment> shipment = shipmentService.getShipmentById(id);
        if (shipment.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Shipment " + id + " does not exist.");
        }
        ShipmentController controller = methodOn(ShipmentController.class);
        EntityModel<Optional<Shipment>> model = EntityModel.of(shipment);
        model.add(linkTo(controller.getShipmentById(id)).withSelfRel());
        model.add(linkTo(controller.getAllShipments()).withRel(IanaLinkRelations.COLLECTION));
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<Object> createShipment(@Validated @RequestBody Shipment newShipment) {
        Shipment createdShipment = shipmentService.createShipment(newShipment);
        if (createdShipment == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Shipment could not be created.");
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Shipment " + createdShipment.getId() + " created successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateShipment(@PathVariable Long id, @Validated @RequestBody Shipment newShipment) {
        Optional<Shipment> shipment = shipmentService.getShipmentById(id);
        if (shipment.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Shipment " + id + " does not exist.");
        }
        shipmentService.updateShipment(id, newShipment);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Shipment " + id + " updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShipment(@PathVariable Long id) {
        Optional<Shipment> shipment = shipmentService.getShipmentById(id);
        if (shipment.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Shipment " + id + " does not exist.");
        }
        shipmentService.deleteShipment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Shipment " + id + " deleted successfully.");
    }
}
