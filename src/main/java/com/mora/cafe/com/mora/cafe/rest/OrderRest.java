package com.mora.cafe.com.mora.cafe.rest;

import com.mora.cafe.com.mora.cafe.dto.request.OrderRequest;
import com.mora.cafe.com.mora.cafe.dto.response.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "order")
public interface OrderRest {
    @PostMapping(path = "/")
    public ResponseEntity<?> newProduct(@Valid @RequestBody OrderRequest orderRequest);

    @GetMapping(path = "/")
    public ResponseEntity<?> getAllProduct();

    @PutMapping(path = "/")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody OrderRequest orderRequest);

    @DeleteMapping(path = "/")
    public ResponseEntity<?> deleteProduct(@Valid @RequestBody Map<String, Long> requestMap);
}
