package dev.lmarchesoti.order_management_test.external.producta.dto;

public record ProductAOrderItem(Long itemId,
                                String description,
                                Long amount,
                                Double unitPrice) {
}
