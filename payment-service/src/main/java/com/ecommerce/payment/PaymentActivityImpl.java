package com.ecommerce.payment; import com.ecommerce.activities.PaymentActivity;
import com.ecommerce.shared.OrderDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentActivityImpl implements PaymentActivity {


    @Override
    public String charge(OrderDto order) {

        //our real gateway logic should work here.

        System.out.println("Processing payment for order: " +order.orderId() +"amount = "+order.amount());

        //Return a fake confirmation.
        return "PAY-"+order.orderId();

    }
}