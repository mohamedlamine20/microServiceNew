package com.mohaDev.inventoryservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryTO {
    private Long id;
    private String skuCode;
    private Integer quantity;
}
