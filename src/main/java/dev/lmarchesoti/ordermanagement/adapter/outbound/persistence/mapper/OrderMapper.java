package dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.mapper;

import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderItemJpaEntity;
import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderJpaEntity;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ShippingInfoMapper.class})
public interface OrderMapper {

    Order toDomain(OrderJpaEntity jpaEntity);

    OrderJpaEntity toEntity(Order order);

    @AfterMapping
    default void setBackLink(@MappingTarget OrderJpaEntity orderJpaEntity) {
        if (orderJpaEntity.getOrderItems() != null) {
            for (OrderItemJpaEntity child : orderJpaEntity.getOrderItems()) {
                child.setOrder(orderJpaEntity);
            }
        }

        if (orderJpaEntity.getShippingInfo() != null) {
            orderJpaEntity.getShippingInfo().setOrder(orderJpaEntity);
        }
    }
}
