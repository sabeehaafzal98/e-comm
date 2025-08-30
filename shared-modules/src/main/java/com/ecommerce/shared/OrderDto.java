package com.ecommerce.shared;

public record OrderDto(Long orderId, Long productId ,int quantity,double amount,String customerId)
{
//amount = total to charge(price*quantity)
}