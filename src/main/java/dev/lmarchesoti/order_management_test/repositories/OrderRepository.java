package dev.lmarchesoti.order_management_test.repositories;

import dev.lmarchesoti.order_management_test.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(Long orderId);

    @Query(value = "select ord " +
            "from Order ord " +
            "where (:customerId is null or ord.customerId == :customerId) " +
            "and (:startCreatedAt is null or ord.createdAt >= :startCreatedAt) " +
            "and (:endCreatedAt is null or ord.createdAt <= :endCreatedAt)")
    Page<Order> findByFilters(Long customerId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt, Pageable pageRequest);
}
