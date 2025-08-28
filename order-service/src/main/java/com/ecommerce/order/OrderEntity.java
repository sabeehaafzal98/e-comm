package com.ecommerce.order;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
@Entity public class OrderEntity extends PanacheEntityBase {
    @Id
    public String orderId;
    public String customerId;
    public long productId;
    public int quantity; }