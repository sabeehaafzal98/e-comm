package com.ecommerce.inventory;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;


@Entity
@Table(name = "inventory")
public class InventoryEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false,unique = true)
    public String name;
    @Column(nullable = false)
    public int quantity;
    @Column(nullable = false)
    public double price;
}
