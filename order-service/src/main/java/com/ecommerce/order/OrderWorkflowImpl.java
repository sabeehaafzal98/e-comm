package com.ecommerce.order;
import com.ecommerce.activities.*;
import com.ecommerce.shared.OrderDto;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;
public class OrderWorkflowImpl implements OrderWorkflow {
    private final ActivityOptions opts = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(5)).setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build()).build();
    private final InventoryActivity inventory = Workflow.newActivityStub(InventoryActivity.class, ActivityOptions.newBuilder(opts).setTaskQueue("INVENTORY_TASK_QUEUE").build());
    private final PaymentActivity payment = Workflow.newActivityStub(PaymentActivity.class, ActivityOptions.newBuilder(opts).setTaskQueue("PAYMENT_TASK_QUEUE").build());
    private final ShippingActivity shipping = Workflow.newActivityStub(ShippingActivity.class, ActivityOptions.newBuilder(opts).setTaskQueue("SHIPPING_TASK_QUEUE").build());
    public String fulfillOrder(OrderDto order){
        inventory.reserveInventory(order);
        payment.processPayment(order);
        Workflow.sleep(Duration.ofSeconds(25));
        shipping.shipOrder(order);
        return "Order Placed Successfully!";
    }
}