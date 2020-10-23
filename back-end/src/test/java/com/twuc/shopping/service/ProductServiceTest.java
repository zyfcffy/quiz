package com.twuc.shopping.service;

import com.twuc.shopping.dto.Product;
import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    ProductEntity productEntity;

    Product product;

    @BeforeEach
    void setUp() {
        productEntity = ProductEntity.builder().id(1).image("").name("可乐").unit("元/瓶").unitPrice(2.3).build();
        product = Product.builder().image("").name("可乐").unit("元/瓶").unitPrice(2.3).build();
    }

    @Test
    void getAllProduct() {
        List<ProductEntity> products = new ArrayList<>();
        products.add(productEntity);
        when(productRepository.findAll()).thenReturn(products);
        productService.getAllProduct();
        verify(productRepository).findAll();
    }

    @Test
    void addProduct() {
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        productService.addProduct(product);
        verify(productRepository).save(productEntity);
    }
}