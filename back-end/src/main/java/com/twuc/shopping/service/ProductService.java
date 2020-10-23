package com.twuc.shopping.service;

import com.twuc.shopping.dto.Product;
import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll().stream()
                .map(item ->
                        Product.builder()
                                .name(item.getName())
                                .unitPrice(item.getUnitPrice())
                                .unit(item.getUnit())
                                .image(item.getImage()).build()).collect(Collectors.toList());
    }

    public void addProduct(Product product){
        ProductEntity productEntity= ProductEntity.builder()
                .name(product.getName())
                .unitPrice(product.getUnitPrice())
                .unit(product.getUnit())
                .image(product.getImage()).build();
        productRepository.save(productEntity);
    }
}
