package com.mora.cafe.wrapper;

import com.mora.cafe.POJO.Order.OrderItem;
import com.mora.cafe.POJO.Order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderWrapper {

    private Long id;
    private String user;
    private List<OrderItem> orderItems;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private LocalDateTime updatedDate;

    // Constructor matching the fields in the query
    public OrderWrapper(Long id, String user, List<OrderItem> orderItems, OrderStatus status, BigDecimal totalAmount, LocalDateTime orderDate, LocalDateTime updatedDate) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
        this.status = status;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.updatedDate = updatedDate;
    }

    // Getters and Setters
}

