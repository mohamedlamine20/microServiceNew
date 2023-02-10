package com.moha.OrderService.Service;



import com.moha.OrderService.DTO.ProductDto;
import com.moha.OrderService.Module.Product;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "product" , url = "https://fakestoreapi.com",path = "/products")
public interface OrderServiceOpenFeign {

    @GetMapping()
    List<Product> findAll();
    @PostMapping
    Product addProduct(@RequestBody  Product product);

    @GetMapping("/{id}")
    Product findById(@PathVariable Long id );

    @GetMapping
    List<Product> findAll(@RequestParam("limit")Integer limit);
}
