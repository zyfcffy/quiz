package com.twuc.shopping.service;

import com.twuc.shopping.dto.ShoppingCart;
import com.twuc.shopping.entity.ShoppingCartEntity;
import com.twuc.shopping.repository.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartServiceTest {
    @Mock
    ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    ShoppingCartService shoppingCartService;

    ShoppingCart shoppingCart;

    ShoppingCartEntity shoppingCartEntity;

    @BeforeEach
    void setUp(){
        shoppingCart = ShoppingCart.builder().name("可乐").unit("元/瓶").unitPrice(2.3).build();
        shoppingCartEntity = ShoppingCartEntity.builder().id(1).name("可乐").unit("元/瓶").unitPrice(2.3).build();
    }

    @Test
    void addToShoppingCart() {
        when(shoppingCartRepository.save(any(ShoppingCartEntity.class))).thenReturn(shoppingCartEntity);
        shoppingCartService.addToShoppingCart(shoppingCart);
        verify(shoppingCartRepository).save(shoppingCartEntity);
    }
}