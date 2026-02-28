package dev.lmarchesoti.ordermanagement.adapter.producta.mapper;

import dev.lmarchesoti.ordermanagement.adapter.producta.dto.ProductAOrder;
import dev.lmarchesoti.ordermanagement.domain.model.ShippingInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductAAddressMapper.class})
public interface ProductAShippingInfoMapper {

    ShippingInfo toDomain(ProductAOrder productAOrder);

}
