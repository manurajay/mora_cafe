package com.mora.cafe.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CategoryService {

    ResponseEntity<String> createCategory(Map<String, String> requestMap);

    ResponseEntity<?> getAllCategory();

    ResponseEntity<?> updateCategory(Map<String, Object> requestMap);

    ResponseEntity<?> deleteCategory(Map<String, Long> requestMap);

}
