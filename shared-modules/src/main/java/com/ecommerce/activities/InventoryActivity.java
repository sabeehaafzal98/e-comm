package com.ecommerce.activities; import com.ecommerce.shared.OrderDto; import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface InventoryActivity {
    //reserve quantity if available. Returns true if reserved.
    @ActivityMethod
    boolean reserve(Long productId, int quantiy);

    //deduct (finalize) inventory after shipping is success
   @ActivityMethod
    void deduct(Long productId, int quantity);

    ////Release a previous reservation(compensation)
    @ActivityMethod
    void release(Long productId,int quantity);

}