package com.moha.OrderService.Mapper;

import com.moha.OrderService.DTO.OrderDto;
import com.moha.OrderService.Module.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {OrderItemMapper.class})
public interface OrderMapper {


    @Mapping(source = "orderItems",target = "orderItemDtos")
    OrderDto to(Order order);

    @InheritInverseConfiguration
    Order from(OrderDto orderItemDto);
}
