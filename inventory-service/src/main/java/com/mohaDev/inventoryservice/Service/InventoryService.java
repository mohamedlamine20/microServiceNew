package com.mohaDev.inventoryservice.Service;

import com.mohaDev.inventoryservice.DTO.InventoryTO;
import com.mohaDev.inventoryservice.Mapper.InventoryMapper;
import com.mohaDev.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
   private final InventoryMapper inventoryMapper;
    private final InventoryRepository inventoryRepository;
    @SneakyThrows
    public List<InventoryTO> isInStock(List<String> skuCode){
        log.info("start");
        Thread.sleep(1000);
        log.info("end");

       return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventoryMapper::to).toList();
    }
}
