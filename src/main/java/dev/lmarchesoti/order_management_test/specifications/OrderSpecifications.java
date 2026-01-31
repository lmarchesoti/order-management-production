package dev.lmarchesoti.order_management_test.specifications;

import dev.lmarchesoti.order_management_test.entities.Order;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSpecifications {

    public static Specification<Order> hasCustomerId(Long customerId) {
        return (root, query, cb) ->
                customerId == null ? cb.conjunction() : cb.equal(root.get("customerId"), customerId);
    }

    public static Specification<Order> createdAfter(LocalDateTime startCreatedAt) {
        return (root, query, cb) ->
                startCreatedAt == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get("createdAt"), startCreatedAt);
    }

    public static Specification<Order> createdBefore(LocalDateTime endCreatedAt) {
        return (root, query, cb) ->
                endCreatedAt == null ? cb.conjunction() : cb.lessThanOrEqualTo(root.get("createdAt"), endCreatedAt);
    }
}
