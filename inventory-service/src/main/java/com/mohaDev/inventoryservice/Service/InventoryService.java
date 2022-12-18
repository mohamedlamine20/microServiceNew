package com.mohaDev.inventoryservice.Service;

import com.mohaDev.inventoryservice.Mapper.InventoryMapper;
import com.mohaDev.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
   private final InventoryMapper inventoryMapper;
    private final InventoryRepository invevtoryRepository;
    public Boolean isInStock(String skuCode){
       return invevtoryRepository.findBySkuCodeEquals(skuCode).isPresent();
    }
}
