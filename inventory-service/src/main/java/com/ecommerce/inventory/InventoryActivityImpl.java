package com.ecommerce.inventory;
import com.ecommerce.activities.InventoryActivity;
import com.ecommerce.shared.InventoryDto;
import com.ecommerce.shared.OrderDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class InventoryActivityImpl implements InventoryActivity {



    @Override
    @ActivateRequestContext
    @Transactional
    public boolean reserve(Long productId, int quantity){
        InventoryEntity entity= InventoryEntity.findById(productId);

        if(entity==null){
            throw new RuntimeException("Product not found "+ productId );
        }
        if(entity.quantity<quantity)
            return false;

        entity.quantity-=quantity;
        //soft reserve by deducting now;
        //or add a reserved column if you prefer.
        entity.persist();
        return  true;
    }

    @Override
    @ActivateRequestContext
    @Transactional
    public void deduct(Long productId, int quantity) {
      //no operations if we already deducted at reserve;
      //keeping for clarity for moving towards shipping success
      //if we prefer final deduction at shipping time, then:
      //-reserve() should not modify quantity as we did before so not needed here, just check availabity at their.
      //then deduct should decrement quantity here.
    }

    @Override
    @ActivateRequestContext
    @Transactional
    public void release(Long productId, int quantity) {
InventoryEntity entity= InventoryEntity.findById(productId);
if(entity==null) return;//Idempotent

        entity.quantity+=quantity;
        entity.persist();
    }
}