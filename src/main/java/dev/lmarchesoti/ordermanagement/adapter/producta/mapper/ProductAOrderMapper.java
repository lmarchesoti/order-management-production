package dev.lmarchesoti.ordermanagement.adapter.producta.mapper;

import dev.lmarchesoti.ordermanagement.adapter.producta.dto.ProductAOrder;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductAShippingInfoMapper.class})
public interface ProductAOrderMapper {

    @Mapping(source = "productAOrder", target = "shippingInfo")
    Order toDomain(ProductAOrder productAOrder);

}
