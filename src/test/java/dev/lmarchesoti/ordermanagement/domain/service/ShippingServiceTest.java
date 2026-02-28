package dev.lmarchesoti.ordermanagement.domain.service;

import dev.lmarchesoti.ordermanagement.domain.model.ShippingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingServiceTest {

    private final ShippingService shippingService = new ShippingService();

    @Test
    @DisplayName("Calculate Shipping should always return a fixed price")
    void testCalculateShipping_whenGivenAnyLocation_shouldReturnAFixedPrice() {
        Double shippingCost = shippingService.calculateShipping(new ShippingInfo());

        assertEquals(10.0, shippingCost, "Should have returned a fixed price");
    }


}