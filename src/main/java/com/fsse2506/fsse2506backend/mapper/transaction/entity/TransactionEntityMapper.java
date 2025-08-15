package com.fsse2506.fsse2506backend.mapper.transaction.entity;

import com.fsse2506.fsse2506backend.data.transaction.entity.TransactionEntity;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import com.fsse2506.fsse2506backend.enumeration.TransactionStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TransactionEntityMapper {

    public TransactionEntity toTransactionEntityMapper (UserEntity buyer){

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setBuyer(buyer);
        transactionEntity.setStatus(TransactionStatus.PREPARE);
        transactionEntity.setDateTime(LocalDateTime.now());
        transactionEntity.setTotal(BigDecimal.ZERO);

        return transactionEntity;
    }
}
