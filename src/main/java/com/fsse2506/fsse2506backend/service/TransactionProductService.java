package com.fsse2506.fsse2506backend.service;

import com.fsse2506.fsse2506backend.data.cartItem.enitty.CartItemEntity;
import com.fsse2506.fsse2506backend.data.transaction.entity.TransactionEntity;
import com.fsse2506.fsse2506backend.data.transactionProduct.entity.TransactionProductEntity;

public interface TransactionProductService {
    TransactionProductEntity createTransactionProduct (TransactionEntity transactionEntity,
                                                       CartItemEntity cartItemEntity);
}
