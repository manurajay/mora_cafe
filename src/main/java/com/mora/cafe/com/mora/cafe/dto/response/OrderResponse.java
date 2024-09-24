package com.mora.cafe.com.mora.cafe.dto.response;

import com.mora.cafe.com.mora.cafe.POJO.Order.OrderStatus;
import com.mora.cafe.com.mora.cafe.POJO.User;
import com.mora.cafe.com.mora.cafe.POJO.Order.OrderItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private User user;
    private List<OrderItem> orderItems;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private LocalDateTime updateDate;
}
