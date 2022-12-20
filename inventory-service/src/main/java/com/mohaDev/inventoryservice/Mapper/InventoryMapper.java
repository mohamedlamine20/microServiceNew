package com.mohaDev.inventoryservice.Mapper;

import com.mohaDev.inventoryservice.DTO.InventoryTO;
import com.mohaDev.inventoryservice.Module.Inventory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    Inventory  from(InventoryTO inventoryTO);
    @Mapping(target = "isStock",expression = "java(isInStock(inventory))")
     InventoryTO to(Inventory  inventory);


    default Boolean isInStock(Inventory  inventory){
     return inventory.getQuantity()>0 ;
    }

}
