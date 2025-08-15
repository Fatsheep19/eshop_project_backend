package com.fsse2506.fsse2506backend.mapper.transactionProduct.entity;

import com.fsse2506.fsse2506backend.data.cartItem.enitty.CartItemEntity;
import com.fsse2506.fsse2506backend.data.transaction.entity.TransactionEntity;
import com.fsse2506.fsse2506backend.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionProductEntityMapper {

    public TransactionProductEntity toTransactionProductEntity (TransactionEntity transaction, CartItemEntity cartItemEntity){
        TransactionProductEntity transactionProductEntity = new TransactionProductEntity();
        transactionProductEntity.setDescription(cartItemEntity.getProduct().getDescription());
        transactionProductEntity.setName(cartItemEntity.getProduct().getName());
        transactionProductEntity.setQuantity(cartItemEntity.getQuantity());
        transactionProductEntity.setImageUrl(cartItemEntity.getProduct().getImageUrl());
        transactionProductEntity.setPrice(cartItemEntity.getProduct().getPrice());
        transactionProductEntity.setStock(cartItemEntity.getProduct().getStock());
        transactionProductEntity.setPid(cartItemEntity.getProduct().getPid());
        transactionProductEntity.setTransaction(transaction);

        return transactionProductEntity;
    }
}
