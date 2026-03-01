package dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.jparepository;

import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long>, JpaSpecificationExecutor<OrderJpaEntity> {

    boolean existsByOrderId(Long orderId);

    @Modifying
    @Query("update OrderJpaEntity order " +
            "set order.status = :status, order.updatedAt = :updatedAt " +
            "where order.orderId = :orderId")
    void updateOrderStatus(Long orderId, String status, LocalDateTime updatedAt);
}
