package dev.lmarchesoti.ordermanagement.domain.service;

import dev.lmarchesoti.ordermanagement.domain.model.OrderItem;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public Double calculatePrice(OrderItem orderItem) {
        return orderItem.getAmount() * orderItem.getUnitPrice();
    }
}
