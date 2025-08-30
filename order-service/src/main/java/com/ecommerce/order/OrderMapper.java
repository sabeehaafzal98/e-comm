package com.ecommerce.order;

import com.ecommerce.shared.OrderDto;
public final class OrderMapper {
    private OrderMapper(){

    }
    public static OrderEntity toEntity(OrderDto d){
        OrderEntity e=new OrderEntity();
        e.orderId=d.orderId();
        e.customerId=d.customerId();
        e.productId=d.productId();
        e.quantity=d.quantity();
        e.status = "NEW";
        e.amount=d.amount();
        return e;
    }
    public static OrderDto toDto(OrderEntity e){
        return new OrderDto(e.orderId,e.productId,e.quantity,e.amount,e.customerId);
    }
}