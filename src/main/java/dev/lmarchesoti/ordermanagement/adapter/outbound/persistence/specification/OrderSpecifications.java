package dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.specification;

import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderJpaEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSpecifications {

    public static Specification<OrderJpaEntity> hasCustomerId(Long customerId) {
        return (root, query, cb) ->
                customerId == null ? cb.conjunction() : cb.equal(root.get("customerId"), customerId);
    }

    public static Specification<OrderJpaEntity> createdAfter(LocalDateTime startCreatedAt) {
        return (root, query, cb) ->
                startCreatedAt == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get("createdAt"), startCreatedAt);
    }

    public static Specification<OrderJpaEntity> createdBefore(LocalDateTime endCreatedAt) {
        return (root, query, cb) ->
                endCreatedAt == null ? cb.conjunction() : cb.lessThanOrEqualTo(root.get("createdAt"), endCreatedAt);
    }
}
