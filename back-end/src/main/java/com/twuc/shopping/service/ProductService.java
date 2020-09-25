package com.twuc.shopping.service;

import com.twuc.shopping.dto.Product;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll().stream()
                .map(item ->
                        Product.builder()
                                .name(item.getName())
                                .unitPrice(item.getUnitPrice())
                                .unit(item.getUnit())
                                .image(item.getImage()).build()).collect(Collectors.toList());
    }
}
