package com.ecommerce.order;
import com.ecommerce.activities.*;
import com.ecommerce.shared.OrderDto;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class OrderWorkflowImpl implements OrderWorkflow {
    private final ActivityOptions opts = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(2)).setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build()).build();
    private final InventoryActivity inventory = Workflow.newActivityStub(InventoryActivity.class, ActivityOptions.newBuilder(opts).setStartToCloseTimeout(Duration.ofMinutes(1)).setTaskQueue("INVENTORY_TASK_QUEUE").build());
    private final PaymentActivity payment = Workflow.newActivityStub(PaymentActivity.class, ActivityOptions.newBuilder(opts).setStartToCloseTimeout(Duration.ofMinutes(1)).setTaskQueue("PAYMENT_TASK_QUEUE").build());
    private final ShippingActivity shipping = Workflow.newActivityStub(ShippingActivity.class, ActivityOptions.newBuilder(opts).setStartToCloseTimeout(Duration.ofMinutes(1)).setTaskQueue("SHIPPING_TASK_QUEUE").build());

    @Override
    public String fulfillOrder(OrderDto order){

        //1. reserve inventory
        boolean reserved = inventory.reserve(order.productId(), order.quantity());
        if(!reserved) {
            return "Failed: Out of stock";
        }
        try{
            //2. charge payment
            String paymentId = payment.charge(order);

            //3. ship

            String tracking = shipping.ship(order);

            //4. (Optional) finalize inventory if you didn't deduct at reserve()
            //inventory.deduct(order.productId,order.quantity());

            return "Order " + order.orderId() + " placed. paymentId= "+paymentId + "trackingId is = " +tracking;
        }
       catch (Exception e ){
            //compensation: release reserved inventory on any failure after reserve.
           inventory.release(order.productId(), order.quantity());
           throw e;
       }
    }
}