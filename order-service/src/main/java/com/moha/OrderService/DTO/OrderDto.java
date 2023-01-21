package com.moha.OrderService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDto {
    private List<OrderItemDto> orderItemDtos;
    private String orderNumber;
}
