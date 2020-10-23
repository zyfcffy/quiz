package com.twuc.shopping.service;

import com.twuc.shopping.dto.Order;
import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    Order order;

    OrderEntity orderEntity;

    @BeforeEach
    void setUp(){
        order = Order.builder().name("可乐").unit("元/瓶").unitPrice(2.3).build();
        orderEntity = OrderEntity.builder().id(1).name("可乐").unit("元/瓶").unitPrice(2.3).build();
    }

    @Test
    void getAllOrders() {
        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(orderEntity);
        when(orderRepository.findAll()).thenReturn(orderEntities);
        orderService.getAllOrders();
        verify(orderRepository).findAll();
    }

    @Test
    void addOrders() {
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        orderService.addOrders(order);
        verify(orderRepository).save(orderEntity);
    }
}