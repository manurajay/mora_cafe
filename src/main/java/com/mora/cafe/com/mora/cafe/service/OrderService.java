package com.mora.cafe.com.mora.cafe.service;

import com.mora.cafe.com.mora.cafe.dto.request.OrderRequest;
import com.mora.cafe.com.mora.cafe.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {
    ResponseEntity<?> createProduct(OrderRequest orderRequest);
    ResponseEntity<?> getAllProducts();
    ResponseEntity<?> updateProduct(OrderRequest orderRequest);
    ResponseEntity<?> deleteProduct(Map<String, Long> requestMap);
}
