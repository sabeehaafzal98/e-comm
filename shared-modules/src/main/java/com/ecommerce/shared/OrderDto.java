package com.ecommerce.shared;

public record OrderDto(Long orderId,String productName,int quantity,double price)
{

}