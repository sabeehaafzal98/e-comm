package com.ecommerce.inventory;
import com.ecommerce.activities.InventoryActivity;
import com.ecommerce.shared.InventoryDto;
import com.ecommerce.shared.OrderDto;
import jakarta.inject.Inject;

public class InventoryActivityImpl implements InventoryActivity {

    @Inject
    InventoryMapper inventoryMapper;

    public void reserveInventory(OrderDto order){
        InventoryEntity entity= new InventoryEntity();
        entity.id= order.orderId();
        entity.quantity=order.quantity();

        if(entity==null|| entity.quantity< order.quantity()){
            throw new RuntimeException("Not Enough Stock");
        }
        entity.quantity-=order.quantity();
        System.out.println("Reserved product: "+ entity.name + "for order " + order.orderId());

    }
}