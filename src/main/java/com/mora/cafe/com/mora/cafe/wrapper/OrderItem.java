package com.mora.cafe.com.mora.cafe.wrapper;


import com.mora.cafe.com.mora.cafe.POJO.Order.Order;
import com.mora.cafe.com.mora.cafe.POJO.Product.NewProduct;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private NewProduct product;

    private int quantity;
    private BigDecimal price;

    // Constructors, Getters, Setters
}

