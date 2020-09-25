package com.twuc.shopping.api;

import com.twuc.shopping.entity.OrderEntity;
import com.twuc.shopping.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

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
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].name",is("可乐1")))
                .andExpect(jsonPath("$[0].unitPrice",is(2.5)))
                .andExpect(jsonPath("$[0].unit",is("元/瓶")))
                .andExpect(jsonPath("$[0].amount",is(2)));
    }

}