package com.mora.cafe.com.mora.cafe.restImpl;

import com.mora.cafe.com.mora.cafe.dto.request.OrderRequest;
import com.mora.cafe.com.mora.cafe.dto.response.OrderResponse;
import com.mora.cafe.com.mora.cafe.rest.OrderRest;
import com.mora.cafe.com.mora.cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController implements OrderRest {

    @Autowired
    OrderService orderService;
    @Override
    public ResponseEntity<?> newOrder(OrderRequest orderRequest) {
        try {
            orderService.createOrder(orderRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllOrder() {
        try {
            orderService.getAllOrders();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateOrder(Map<String, Object> orderRequest) {
        try {
            orderService.updateOrder(orderRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> deleteOrder(Map<String, Long> requestMap) {
        try {
            orderService.deleteOrder(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getOrder(Integer id) {
        try {
            orderService.getOrderById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
