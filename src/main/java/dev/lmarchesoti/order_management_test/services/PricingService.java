package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.entities.OrderItem;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public Double calculatePrice(OrderItem orderItem) {
        return orderItem.getAmount() * orderItem.getUnitPrice();
    }
}
