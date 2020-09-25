package com.twuc.shopping.api;

import com.twuc.shopping.dto.Order;
import com.twuc.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/order")
    public ResponseEntity<Object> addOrder(@RequestBody Order order){
        orderService.addOrders(order);
        return ResponseEntity.created(null).build();
    }
}
