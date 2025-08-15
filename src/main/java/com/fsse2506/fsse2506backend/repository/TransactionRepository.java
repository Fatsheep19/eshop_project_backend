package com.fsse2506.fsse2506backend.repository;

import com.fsse2506.fsse2506backend.data.transaction.entity.TransactionEntity;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository <TransactionEntity, Integer> {
    Optional<TransactionEntity> findByTid(Integer tid);

    Optional<TransactionEntity> findByBuyerAndTid(UserEntity buyer, Integer tid);
}
