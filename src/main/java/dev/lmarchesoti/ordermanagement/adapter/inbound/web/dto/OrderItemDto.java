package dev.lmarchesoti.ordermanagement.adapter.inbound.web.dto;

public record OrderItemDto(
        Long itemId,
        String description,
        Long amount,
        Double unitPrice,
        Double totalPrice
) {
}
