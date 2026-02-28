package dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.mapper;

import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderItemJpaEntity;
import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderJpaEntity;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import dev.lmarchesoti.ordermanagement.domain.model.OrderItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItem toDomain(OrderItemJpaEntity orderItemJpaEntity);
    OrderItemJpaEntity toEntity(OrderItem order);
}
