package com.mora.cafe.com.mora.cafe.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/product")
public interface ProductRest {
    @PostMapping(path = "/")
    public ResponseEntity<?> newProduct(@Valid @RequestBody Map<String, Object> requestMap);

    @GetMapping(path = "/")
    public ResponseEntity<?> getAllProducts();

    @PutMapping(path = "/")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Map<String, Object> requestMap);

    @PutMapping(path = "/{id}")
    public ResponseEntity<Boolean> changeAvailable(@PathVariable Long id);

    @DeleteMapping(path = "/")
    public ResponseEntity<?> deleteProduct(@Valid @RequestBody Map<String, Long> requestMap);
}
