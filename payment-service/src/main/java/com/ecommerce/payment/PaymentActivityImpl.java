package com.ecommerce.payment; import com.ecommerce.activities.PaymentActivity;
import com.ecommerce.shared.OrderDto;
public class PaymentActivityImpl implements PaymentActivity {
    public void processPayment(OrderDto order)
    {
        System.out.println("Processing payment for order: "+order.orderId());
    }
}