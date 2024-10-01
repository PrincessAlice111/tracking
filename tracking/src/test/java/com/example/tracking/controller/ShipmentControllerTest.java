package com.example.tracking.controller;

import java.util.List;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.tracking.model.Shipment;
import com.example.tracking.service.ShipmentService;

@WebMvcTest(ShipmentController.class)
public class ShipmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService shipmentService;

    @Test
    void getAllShipmentsSuccess() throws Exception {
        // Arrange.
        List<Shipment> shipments = List.of(
                new Shipment(1L, "bird seeds", "cancelled", "Address One 111", "Recipient One"),
                new Shipment(2L, "cat kibble", "delivered", "Address Two 222", "Recipient Two"),
                new Shipment(3L, "dog kibble", "pending", "Address Three 333", "Recipient Three"));
        when(shipmentService.getAllShipments()).thenReturn(shipments);

        // Act and assert.
        mockMvc.perform(get("/shipments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.shipmentList", Matchers.hasSize(3)))
                .andExpect(jsonPath("$._embedded.shipmentList[0].product", Matchers.is("bird seeds")))
                .andExpect(jsonPath("$._embedded.shipmentList[1].product", Matchers.is("cat kibble")))
                .andExpect(jsonPath("$._embedded.shipmentList[2].product", Matchers.is("dog kibble")))
                .andExpect(jsonPath("$._embedded.shipmentList[0].status", Matchers.is("cancelled")))
                .andExpect(jsonPath("$._embedded.shipmentList[1].status", Matchers.is("delivered")))
                .andExpect(jsonPath("$._embedded.shipmentList[2].status", Matchers.is("pending")))
                .andExpect(jsonPath("$._embedded.shipmentList[0].address", Matchers.is("Address One 111")))
                .andExpect(jsonPath("$._embedded.shipmentList[1].address", Matchers.is("Address Two 222")))
                .andExpect(jsonPath("$._embedded.shipmentList[2].address", Matchers.is("Address Three 333")))
                .andExpect(jsonPath("$._embedded.shipmentList[0].recipient", Matchers.is("Recipient One")))
                .andExpect(jsonPath("$._embedded.shipmentList[1].recipient", Matchers.is("Recipient Two")))
                .andExpect(jsonPath("$._embedded.shipmentList[2].recipient", Matchers.is("Recipient Three")))
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    void getAllShipmentsEmptyList() throws Exception {
        // Arrange.
        List<Shipment> shipments = List.of();
        when(shipmentService.getAllShipments()).thenReturn(shipments);

        // Act and assert.
        mockMvc.perform(get("/shipments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    void getShipmentByIdSuccess() throws Exception {
        // Arrange.
        Optional<Shipment> shipment = Optional.of(
                new Shipment(1L, "bird seeds", "cancelled", "Address One 111", "Recipient One"));
        when(shipmentService.getShipmentById(1L)).thenReturn(shipment);

        // Act and assert.
        mockMvc.perform(get("/shipments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product", Matchers.is("bird seeds")))
                .andExpect(jsonPath("$.status", Matchers.is("cancelled")))
                .andExpect(jsonPath("$.address", Matchers.is("Address One 111")))
                .andExpect(jsonPath("$.recipient", Matchers.is("Recipient One")))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.collection").exists());
    }

    @Test
    void getShipmentByIdNotFound() throws Exception {
        // Arrange.
        Optional<Shipment> shipment = Optional.empty();
        when(shipmentService.getShipmentById(1L)).thenReturn(shipment);

        // Act and assert.
        mockMvc.perform(get("/shipments/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Shipment 1 does not exist."));
    }
}
