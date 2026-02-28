package dev.lmarchesoti.ordermanagement.application.query;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public record ListOrdersQuery(
        Long customerId,
        LocalDateTime startCreatedAt,
        LocalDateTime endCreatedAt,
        Pageable pageable) {
}
