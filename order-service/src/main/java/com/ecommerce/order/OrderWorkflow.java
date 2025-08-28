package com.ecommerce.order;
import com.ecommerce.shared.OrderDto;
import io.temporal.workflow.*;
@WorkflowInterface
public interface OrderWorkflow {
    @WorkflowMethod String fulfillOrder(OrderDto order);
}