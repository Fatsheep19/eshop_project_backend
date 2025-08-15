package com.fsse2506.fsse2506backend.repository;

import com.fsse2506.fsse2506backend.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByPid(Integer pid);
}
