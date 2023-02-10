package com.moha.OrderService.Controller;

import com.moha.OrderService.DTO.OrderDto;
import com.moha.OrderService.DTO.ProductDto;
import com.moha.OrderService.Module.Product;
import com.moha.OrderService.Service.OrderService;
import com.moha.OrderService.Service.OrderServiceOpenFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
   private final OrderService orderService;
   private final OrderServiceOpenFeign orderServiceOpenFeign;
    @PostMapping("/create")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    //@TimeLimiter(name = "inventory" )
   // @Retry(name = "inventory")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(orderDto));

    }
    @GetMapping("/All")
    public Page<OrderDto> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "100") Integer pageSize,
                                 @RequestParam(defaultValue = "id") String sortBy,
                                 @RequestParam(defaultValue = "asc") String direction){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return orderService.findAll(pageable);
    }

    @GetMapping("/test")
    public List<Product>test(){
        System.out.println("--------------------------");
        System.out.println(orderServiceOpenFeign.findAll().size());
        Product product=new Product();
        product.setTitle("aaaaaaaaaa");
        product.setPrice(13.5);
        product.setDescription("gfhjh");
        product.setImage("qsdfghjkl");
        System.out.println(orderServiceOpenFeign.addProduct(product));

        System.out.println(orderServiceOpenFeign.findById(1l));
        System.out.println(orderServiceOpenFeign.findAll(5));
        return orderServiceOpenFeign.findAll();
    }
    public ResponseEntity<OrderDto> fallbackMethod(OrderDto orderDto ,RuntimeException runtimeException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallBackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderDto orderDto) {
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderDto));
    }

    public CompletableFuture<String> fallBackMethod(OrderDto orderDto, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
    }


}
