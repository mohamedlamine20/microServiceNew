package com.moha.OrderService.Service;

import brave.Span;
import brave.Tracer;
import com.moha.OrderService.DTO.InventoryTO;
import com.moha.OrderService.DTO.OrderDto;
import com.moha.OrderService.DTO.OrderItemDto;
import com.moha.OrderService.DTO.OrderNotification;
import com.moha.OrderService.Mapper.OrderMapper;
import com.moha.OrderService.Module.Order;
import com.moha.OrderService.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final WebClient.Builder webClient;

    private final KafkaTemplate<String,OrderNotification> kafkaTemplate;

    private final Tracer tracer;


    public OrderDto createOrder(OrderDto  orderDto) throws Exception {
        List<String>skuCodes = orderDto.getOrderItemDtos().stream().map(orderItemDto -> orderItemDto.getSkuCode()).toList();
        InventoryTO[] inventoryTOS = webClient.build().get()
                .uri("http://inventory-service/inventory/isInStock",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryTO[].class)
                .block();



        if(Arrays.stream(inventoryTOS).allMatch(inventoryTO -> inventoryTO.isStock())){

            orderDto.setOrderNumber(UUID.randomUUID().toString());
            OrderDto orderDto1 = orderMapper.to(orderRepository.save(orderMapper.from(orderDto)));
            kafkaTemplate.send("notificationTopic",new OrderNotification(orderDto1.getOrderNumber()));
        return orderDto1;
        }
        else throw new Exception("stock");

    }



    public String placeOrder(OrderDto orderDto) {


        List<String> skuCodes = orderDto.getOrderItemDtos().stream()
                .map(OrderItemDto::getSkuCode)
                .toList();

        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");

        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(inventoryServiceLookup.start())) {

            inventoryServiceLookup.tag("call", "inventory-service");
            // Call Inventory Service, and place order if product is in
            // stock
            InventoryTO[] inventoryTOS = webClient.build().get()
                    .uri("http://inventory-service/inventory/isInStock",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryTO[].class)
                    .block();

            boolean allProductsInStock = Arrays.stream(inventoryTOS)
                    .allMatch(InventoryTO::isStock);

            if (allProductsInStock) {
                orderDto.setOrderNumber(UUID.randomUUID().toString());
                Order order = orderRepository.save(orderMapper.from(orderDto));
                kafkaTemplate.send("notificationTopic",new OrderNotification(order.getOrderNumber()));
                return "Order Placed Successfully";
            } else {
                throw new IllegalArgumentException("Product is not in stock, please try again later");
            }
        } finally {
            inventoryServiceLookup.flush();
        }
    }

        public Page<OrderDto> findAll(Pageable pageable){
            Page<Order> orderDtos=orderRepository.findAll(pageable);
       return new PageImpl<>(orderDtos.getContent().stream().map(order -> orderMapper.to(order)).collect(Collectors.toList()),pageable,orderDtos.getTotalElements());

    }
}
