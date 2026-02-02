package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.entities.ShippingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShippingServiceTest {

    private final ShippingService shippingService = new ShippingService();

    @Test
    @DisplayName("Calculate Shipping should always return a fixed price")
    void testCalculateShipping_whenGivenAnyLocation_shouldReturnAFixedPrice() {
        Double shippingCost = shippingService.calculateShipping(new ShippingInfo());

        assertEquals(10.0, shippingCost, "Should have returned a fixed price");
    }


}