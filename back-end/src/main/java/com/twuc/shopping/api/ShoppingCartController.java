package com.twuc.shopping.api;

import com.twuc.shopping.dto.ShoppingCart;
import com.twuc.shopping.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {
    final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/shoppingCart")
    public ResponseEntity<Object> addToShoppingCart(ShoppingCart shoppingCart){
        shoppingCartService.addToShoppingCart(shoppingCart);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/shoppingCarts")
    public ResponseEntity<Object> deleteAllShoppingCarts(){
        shoppingCartService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
