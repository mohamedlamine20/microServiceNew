package com.moha.OrderService.Controller;

import com.moha.OrderService.DTO.OrderDto;
import com.moha.OrderService.Service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
   private final OrderService orderService;
    @PostMapping("/create")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    //@TimeLimiter(name = "inventory" )
   // @Retry(name = "inventory")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }


    public ResponseEntity<OrderDto> fallbackMethod(OrderDto orderDto ,RuntimeException runtimeException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

}
