package com.mohaDev.inventoryservice.Service;

import com.mohaDev.inventoryservice.DTO.InventoryTO;
import com.mohaDev.inventoryservice.Mapper.InventoryMapper;
import com.mohaDev.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
   private final InventoryMapper inventoryMapper;
    private final InventoryRepository invevtoryRepository;
    public List<InventoryTO> isInStock(List<String> skuCode){
       return invevtoryRepository.findBySkuCodeIn(skuCode).stream().map(inventory
               -> inventoryMapper.to(inventory)).toList();
    }
}
