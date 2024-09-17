package com.mora.cafe.com.mora.cafe.rest;

import com.mora.cafe.com.mora.cafe.dto.request.NewCategory;
import com.mora.cafe.com.mora.cafe.dto.response.GetCategory;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/category")
public interface CategoryRest {

    @PostMapping(path = "/")
    public ResponseEntity<String> newCategory(@Valid @RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/")
    public ResponseEntity<?> getAllCategory();

    @PutMapping(path = "/")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody Map<String, Long> requestMap);

    @DeleteMapping(path = "/")
    public ResponseEntity<?> deleteCategory(@Valid @RequestBody Map<String, Long> requestMap);
}
