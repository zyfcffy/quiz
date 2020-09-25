package com.twuc.shopping.service;

import com.twuc.shopping.dto.Order;
import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll().stream().map(
                item-> Order.builder().name(item.getName())
                .amount(item.getAmount())
                .unit(item.getUnit())
                .unitPrice(item.getUnitPrice()).build()
        ).collect(Collectors.toList());
    }

    public void addOrders(Order order){
        OrderEntity orderEntity =OrderEntity.builder()
                .name(order.getName())
                .amount(order.getAmount())
                .unitPrice(order.getUnitPrice())
                .unit(order.getUnit()).build();
        orderRepository.save(orderEntity);
    }

    public void deleteById(Integer id){
        orderRepository.deleteById(id);
    }
}
