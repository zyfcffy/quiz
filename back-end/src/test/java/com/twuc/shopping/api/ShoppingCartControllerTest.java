package com.twuc.shopping.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.dto.ShoppingCart;
import com.twuc.shopping.entity.ShoppingCartEntity;
import com.twuc.shopping.repository.ShoppingCartRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ShoppingCartControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @AfterEach
    void clear() {
        shoppingCartRepository.deleteAll();
    }

    @Test
    void shouldAddToShoppingCartSuccess() throws Exception {
        ShoppingCart shoppingCart = ShoppingCart.builder().amount(2).name("可乐1").unit("元/瓶").unitPrice(2.5).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(shoppingCart);
        mockMvc.perform(post("/shoppingCart")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteAllSuccess() throws Exception {
        ShoppingCartEntity shoppingCartEntity1 = ShoppingCartEntity.builder()
                .amount(2).name("可乐1").unit("元/瓶").unitPrice(2.5).build();
        shoppingCartRepository.save(shoppingCartEntity1);
        ShoppingCartEntity shoppingCartEntity2 = ShoppingCartEntity.builder()
                .amount(2).name("可乐1").unit("元/瓶").unitPrice(2.5).build();
        shoppingCartRepository.save(shoppingCartEntity2);
        ShoppingCartEntity shoppingCartEntity3 = ShoppingCartEntity.builder()
                .amount(2).name("可乐1").unit("元/瓶").unitPrice(2.5).build();
        shoppingCartRepository.save(shoppingCartEntity3);
        mockMvc.perform(delete("/shoppingCarts"))
                .andExpect(status().isNoContent());
        assertEquals(0,shoppingCartRepository.findAll().size());
    }
}