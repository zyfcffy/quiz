package com.twuc.shopping.api;

import com.twuc.shopping.dto.Product;
import com.twuc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @PostMapping("/product")
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.created(null).build();
    }
}
