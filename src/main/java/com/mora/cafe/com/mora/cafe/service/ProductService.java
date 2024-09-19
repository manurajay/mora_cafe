package com.mora.cafe.com.mora.cafe.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProductService {
    ResponseEntity<?> createProduct(Map<String, Object> requestMap);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> updateProduct(Map<String, Object> requestMap);

    ResponseEntity<?> deleteProduct(Map<String, Long> requestMap);
    ResponseEntity<Boolean> updateAvailable(Long id);
}
