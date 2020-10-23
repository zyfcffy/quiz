package com.twuc.shopping.repository;

import com.twuc.shopping.entity.ShoppingCartEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCartEntity,Integer> {
    List<ShoppingCartEntity> findAll();
}
