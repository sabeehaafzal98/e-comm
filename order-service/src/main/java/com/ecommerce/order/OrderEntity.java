package com.ecommerce.order;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name = "orders")
public class OrderEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long orderId;

    @Column(nullable = false)
    public String customerId;

    @Column(nullable = false)
    public Long productId;

    @Column(nullable = false)
    public int quantity;

    @Column(nullable = false)
    public double amount;

    @Column
    public String status;
    //eg. new,paid,shipped,failed

}
