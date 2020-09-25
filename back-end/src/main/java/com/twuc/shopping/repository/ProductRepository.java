package com.twuc.shopping.repository;

import com.twuc.shopping.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {
    List<ProductEntity> findAll();
}
