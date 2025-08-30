package com.ecommerce.shipping; import com.ecommerce.activities.ShippingActivity;
import com.ecommerce.shared.OrderDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShippingActivityImpl implements ShippingActivity {

    @Override
    public String ship(OrderDto order) {
        String tracking = "TRACK-"+order.orderId();
        System.out.println("shipping order "+ order.orderId() + " to customer "+ order.customerId() +
                 "tracking = " + tracking);
        return tracking;
    }
}