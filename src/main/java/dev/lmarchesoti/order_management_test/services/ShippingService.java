package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.entities.ShippingInfo;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double calculateShipping(ShippingInfo shippingInfo) {
        return 10.0; // fixed value stub
    }
}
