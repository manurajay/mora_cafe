package com.mora.cafe.com.mora.cafe.serviceImpl;

import com.mora.cafe.com.mora.cafe.POJO.Order.Order;
import com.mora.cafe.com.mora.cafe.POJO.Order.OrderItem;
import com.mora.cafe.com.mora.cafe.POJO.Order.OrderStatus;
import com.mora.cafe.com.mora.cafe.dao.OrderDao;
import com.mora.cafe.com.mora.cafe.dao.UserDao;
import com.mora.cafe.com.mora.cafe.dto.request.OrderRequest;
import com.mora.cafe.com.mora.cafe.dto.response.OrderResponse;
import com.mora.cafe.com.mora.cafe.service.OrderService;
import com.mora.cafe.com.mora.cafe.wrapper.OrderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<?> createOrder(OrderRequest orderRequest) {
        try {
            Order order1 = orderMapping(orderRequest);
            orderDao.save(order1);
            return new ResponseEntity<>(order1, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllOrders() {
        try {
            List<OrderWrapper> allProducts = orderDao.getAllOrders();
            return new ResponseEntity<>(allProducts, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Order> getOrderById(Integer id) {
        try {
            Order order = orderDao.getOrderById(id);
            if (!Objects.isNull(order)) {
                return new ResponseEntity<>(order, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> updateOrder(Map<String, Object> orderRequest) {
        try {
            Order order = orderDao.getOrderById(orderRequest.get("orderId"));
            if (!Objects.isNull(order)) {
                order.setStatus((OrderStatus) orderRequest.get("status"));
                order.setTotalAmount((BigDecimal) orderRequest.get("totalAmount"));
                orderDao.save(order);
                return new ResponseEntity<>(order, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> updateOrderItem(Map<String, Object> orderRequest) {
        try {
            Order order = orderDao.getOrderById(orderRequest.get("orderId"));
            if (!Objects.isNull(order)) {
                List<OrderItem> orderItems = order.getOrderItems();

            }
        }
    }


    @Override
    public ResponseEntity<?> deleteOrder(Map<String, Long> requestMap) {
        return null;
    }

    private Order orderMapping(OrderRequest orderRequest) {
        Order newOrder = new Order();
        newOrder.setUser(userDao.findByEmailId(orderRequest.getUserEmail()));
        newOrder.setOrderItems(orderRequest.getOrderItems());
        newOrder.setStatus(OrderStatus.PENDING);
        newOrder.setTotalAmount(orderRequest.getTotalAmount());
        return newOrder;
    }
}
