package com.mora.cafe.dto.request;

import com.mora.cafe.POJO.Order.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequest {
    private String userEmail;
    private List<OrderItem> orderItems;
    private BigDecimal totalAmount;

}


