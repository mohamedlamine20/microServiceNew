package com.moha.OrderService.Mapper;

import com.moha.OrderService.DTO.OrderItemDto;
import com.moha.OrderService.Module.OrderItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

  OrderItemDto  to(OrderItem orderItem);

  @InheritInverseConfiguration
  OrderItem from(OrderItemDto orderItemDto);
}
