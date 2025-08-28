package com.ecommerce.shared;

public record OrderDto(String orderId,String customerId,Long productId,int quantity)
{

}