package com.twuc.shopping.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.dto.Product;
import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;

    @AfterEach
    void clear() {
        productRepository.deleteAll();
    }
    @Test
    void shouldGetAllProductList() throws Exception {
        ProductEntity productEntity =ProductEntity.builder()
                .name("可乐1")
                .unit("元/瓶")
                .unitPrice(2.5).build();
        productRepository.save(productEntity);
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].name",is("可乐1")))
                .andExpect(jsonPath("$[0].unitPrice",is(2.5)))
                .andExpect(jsonPath("$[0].unit",is("元/瓶")));
    }

    @Test
    void shouldAddProductSuccess() throws Exception {
        Product product = Product.builder().name("可乐2").unit("元/瓶").unitPrice(1.0).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);
        mockMvc.perform(post("/product")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}