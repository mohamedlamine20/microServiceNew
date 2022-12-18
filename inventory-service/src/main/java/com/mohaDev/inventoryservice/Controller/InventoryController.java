package com.mohaDev.inventoryservice.Controller;

import com.mohaDev.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService  inventoryService;
    @GetMapping("/isInStock/{skuCode}")
    public ResponseEntity<Boolean>isInStock(@PathVariable String  skuCode ){
        return ResponseEntity.ok(inventoryService.isInStock(skuCode));
    }
}
