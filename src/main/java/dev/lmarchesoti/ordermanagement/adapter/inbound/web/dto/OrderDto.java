package dev.lmarchesoti.ordermanagement.adapter.inbound.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long orderId,
        Long customerId,
        String status,
        LocationDto origin,
        LocationDto destination,
        Integer totalItems,
        Double totalPrice,
        Double shippingCost,
        Double totalPriceWithShipping,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<OrderItemDto> orderItems) {
}
