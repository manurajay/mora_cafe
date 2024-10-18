//package com.mora.cafe.com.mora.cafe.dao;
//
//import com.mora.cafe.com.mora.cafe.POJO.Order.Order;
//import com.mora.cafe.com.mora.cafe.dto.response.OrderResponse;
//import com.mora.cafe.com.mora.cafe.wrapper.OrderWrapper;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface OrderDao extends JpaRepository<Order, Integer> {
//
//    @Query("SELECT new com.mora.cafe.com.mora.cafe.wrapper.OrderWrapper(o.id, o.user.username, o.orderItems, o.status, o.totalAmount, o.orderDate, o.updatedDate) FROM Order o")
//    List<OrderWrapper> getAllOrders();
//
//    Order getOrderById(Object id);
//
//    Order getOderByEmailAndOrderId(String email);
//
//    Order getOrderByUserId(int userId);
//
//
//
//}
