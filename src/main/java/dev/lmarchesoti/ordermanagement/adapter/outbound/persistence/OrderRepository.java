package dev.lmarchesoti.ordermanagement.adapter.outbound.persistence;

import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderJpaEntity;
import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.mapper.OrderMapper;
import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.jparepository.OrderJpaRepository;
import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.specification.OrderSpecifications;
import dev.lmarchesoti.ordermanagement.application.port.out.OrderPersistencePort;
import dev.lmarchesoti.ordermanagement.application.query.ListOrdersQuery;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderRepository implements OrderPersistencePort {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    private final OrderJpaRepository orderJpaRepository;

    private final OrderMapper orderMapper;

    @Override
    public Page<Order> listOrders(ListOrdersQuery listOrdersQuery) {

        Specification<OrderJpaEntity> specification = Specification.allOf(
                OrderSpecifications.hasCustomerId(listOrdersQuery.customerId()),
                OrderSpecifications.createdAfter(listOrdersQuery.startCreatedAt()),
                OrderSpecifications.createdBefore(listOrdersQuery.endCreatedAt())
        );

        return orderJpaRepository.findAll(specification, listOrdersQuery.pageable()).map(orderMapper::toDomain);
    }

    @Override
    public void create(Order order) {
        orderJpaRepository.save(orderMapper.toEntity(order));
    }

    @Override
    public boolean orderExists(Long orderId) {
        return orderJpaRepository.existsByOrderId(orderId);
    }

    @Override
    public void updateOrderStatus(Long orderId, String status, LocalDateTime updatedAt) {
        orderJpaRepository.updateOrderStatus(orderId, status, updatedAt);
    }
}
