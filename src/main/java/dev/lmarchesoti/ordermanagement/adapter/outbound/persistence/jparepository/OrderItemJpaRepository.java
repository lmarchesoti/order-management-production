package dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.jparepository;

import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemJpaRepository extends JpaRepository<OrderItemJpaEntity, Long> {
}
