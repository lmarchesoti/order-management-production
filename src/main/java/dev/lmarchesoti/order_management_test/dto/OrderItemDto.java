package dev.lmarchesoti.order_management_test.dto;

import dev.lmarchesoti.order_management_test.entities.OrderItem;

public record OrderItemDto(
        Long itemId,
        String description,
        Long amount,
        Double unitPrice,
        Double totalPrice
) {

    public OrderItemDto(OrderItem orderItem) {
        this(orderItem.getItemId(),
                orderItem.getDescription(),
                orderItem.getAmount(),
                orderItem.getUnitPrice(),
                orderItem.getTotalPrice());
    }
}
