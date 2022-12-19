package com.mohaDev.inventoryservice.Controller;

import com.mohaDev.inventoryservice.DTO.InventoryTO;
import com.mohaDev.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService  inventoryService;
    @GetMapping("/isInStock")
    public ResponseEntity<List<InventoryTO>>isInStock(@RequestParam List<String>  skuCode ){
        return ResponseEntity.ok(inventoryService.isInStock(skuCode));
    }
}
