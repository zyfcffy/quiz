package com.twuc.shopping.service;

import com.twuc.shopping.dto.ShoppingCart;
import com.twuc.shopping.entity.ShoppingCartEntity;
import com.twuc.shopping.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    final
    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void addToShoppingCart(ShoppingCart shoppingCart){
        ShoppingCartEntity shoppingCartEntity=ShoppingCartEntity.builder()
                .amount(shoppingCart.getAmount())
                .name(shoppingCart.getName())
                .unit(shoppingCart.getUnit())
                .unitPrice(shoppingCart.getUnitPrice())
                .build();
        shoppingCartRepository.save(shoppingCartEntity);
    }

    public void deleteAll(){
        shoppingCartRepository.deleteAll();
    }
}
