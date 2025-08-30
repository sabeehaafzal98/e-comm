package com.ecommerce.activities;
import com.ecommerce.shared.OrderDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PaymentActivity {

    //returns a payment confirmation id; throws on failure.
    @ActivityMethod
    String charge(OrderDto order);
}