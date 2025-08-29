package com.ecommerce.activities; import com.ecommerce.shared.OrderDto; import io.temporal.activity.ActivityInterface;
@ActivityInterface
public interface InventoryActivity {
    void reserveInventory(OrderDto order); }