package com.ecommerce.activities;
import com.ecommerce.shared.OrderDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ShippingActivity {

    //returns a tracking number throws on failure
    @ActivityMethod
    String ship(OrderDto order); }