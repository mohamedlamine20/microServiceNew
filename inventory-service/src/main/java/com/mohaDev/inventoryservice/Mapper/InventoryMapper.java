package com.mohaDev.inventoryservice.Mapper;

import com.mohaDev.inventoryservice.DTO.InventoryTO;
import com.mohaDev.inventoryservice.Module.Inventory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    Inventory  from(InventoryTO inventoryTO);
    @InheritInverseConfiguration
    InventoryTO to(Inventory  inventory);

}
