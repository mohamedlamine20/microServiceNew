package com.moha.OrderService.Service;

import com.moha.OrderService.DTO.OrderDto;
import com.moha.OrderService.Mapper.OrderMapper;
import com.moha.OrderService.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderDto createOrder(OrderDto  orderDto){
        orderDto.setOrderNumber(UUID.randomUUID().toString());
        return  orderMapper.to(orderRepository.save(orderMapper.from(orderDto)));
    }
}
