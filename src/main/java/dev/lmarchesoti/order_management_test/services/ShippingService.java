package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.entities.Address;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double calculateShipping(Address origin, Address destination) {
        return 0.0; // fixed value stub
    }
}
