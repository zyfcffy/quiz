package com.twuc.shopping.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.dto.Order;
import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    OrderRepository orderRepository;

    @AfterEach
    void clear() {
        orderRepository.deleteAll();
    }

    @Test
    void shouldGetAllOrderList() throws Exception {
        OrderEntity orderEntity = OrderEntity.builder()
                .name("可乐1")
                .unitPrice(2.5)
                .unit("元/瓶")
                .amount(2).build();
        orderRepository.save(orderEntity);
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("可乐1")))
                .andExpect(jsonPath("$[0].unitPrice", is(2.5)))
                .andExpect(jsonPath("$[0].unit", is("元/瓶")))
                .andExpect(jsonPath("$[0].amount", is(2)));
    }

    @Test
    void shouldAddOrderSuccess() throws Exception {
        Order order = Order.builder().name("可乐1").amount(2).unit("元/瓶").unitPrice(2.5).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);
        mockMvc.perform(post("/order")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<OrderEntity> orders = orderRepository.findAll();
        assertEquals(1,orders.size());
        assertEquals("可乐1",orders.get(0).getName());
        assertEquals(2,orders.get(0).getAmount());
    }

}