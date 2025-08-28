package com.ecommerce.inventory;
import com.ecommerce.activities.InventoryActivity;
import com.ecommerce.shared.OrderDto;
import jakarta.inject.Inject;

public class InventoryActivityImpl implements InventoryActivity {

    @Inject
    ProductRepository productRepository;

    public void reserveInventory(OrderDto order){
        ProductEntity product = productRepository.findById(order.productId());
        if(product==null|| product.quantity< order.quantity()){
            throw new RuntimeException("Not Enough Stock");
        }
        product.quantity-=order.quantity();
        System.out.println("Reserved product: "+ product.name + "for order " + order.orderId());

    }
}