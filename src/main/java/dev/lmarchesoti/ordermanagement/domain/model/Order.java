package dev.lmarchesoti.ordermanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    Long orderId;
    Long customerId;
    String status;
    ShippingInfo shippingInfo = new ShippingInfo();
    Integer totalItems;
    Double totalPrice;
    Double shippingCost;
    Double totalPriceWithShipping;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<OrderItem> orderItems = new ArrayList<>();

    public void addShippingCost(Double value) {
        this.shippingCost = value;
        this.totalPriceWithShipping = totalPrice + value;
    }
}
