package dev.lmarchesoti.ordermanagement.adapter.producta.dto;

public record ProductAOrderItem(Long itemId,
                                String description,
                                Long amount,
                                Double unitPrice) {
}
