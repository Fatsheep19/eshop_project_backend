package com.fsse2506.fsse2506backend.repository;

import com.fsse2506.fsse2506backend.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionProductRepository extends CrudRepository <TransactionProductEntity, Integer> {
}
