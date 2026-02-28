package dev.lmarchesoti.ordermanagement.domain.service;

import dev.lmarchesoti.ordermanagement.domain.model.ShippingInfo;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double calculateShipping(ShippingInfo shippingInfo) {
        return 10.0; // fixed value stub
    }
}
