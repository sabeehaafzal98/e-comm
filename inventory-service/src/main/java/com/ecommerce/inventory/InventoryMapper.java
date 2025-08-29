package com.ecommerce.inventory;

import com.ecommerce.shared.InventoryDto;
import com.ecommerce.shared.OrderDto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

public final class InventoryMapper {

   private InventoryMapper(){

    }

    public static InventoryDto toDto(InventoryEntity entity){
       return new InventoryDto(entity.id, entity.name, entity.quantity, entity.price);
    }
    public static InventoryEntity toEntity(InventoryDto dto){
        InventoryEntity entity=new InventoryEntity();
        entity.id= dto.id();
        entity.name= dto.name();
        entity.quantity= dto.quantity();
        entity.price= dto.price();
        return entity;
    }
}