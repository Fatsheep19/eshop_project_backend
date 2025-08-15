package com.fsse2506.fsse2506backend.service.impl;

import com.fsse2506.fsse2506backend.data.cartItem.enitty.CartItemEntity;
import com.fsse2506.fsse2506backend.data.transaction.entity.TransactionEntity;
import com.fsse2506.fsse2506backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2506.fsse2506backend.mapper.transactionProduct.entity.TransactionProductEntityMapper;
import com.fsse2506.fsse2506backend.repository.TransactionProductRepository;
import com.fsse2506.fsse2506backend.service.TransactionProductService;
import org.springframework.stereotype.Service;


@Service
public class TransactionProductServiceImpl implements TransactionProductService {

    private final TransactionProductEntityMapper transactionProductEntityMapper;
    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductEntityMapper transactionProductEntityMapper, TransactionProductRepository transactionProductRepository) {
        this.transactionProductEntityMapper = transactionProductEntityMapper;
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity,
                                                             CartItemEntity cartItemEntity){

        return transactionProductRepository.save(transactionProductEntityMapper.toTransactionProductEntity(transactionEntity, cartItemEntity)) ;

    }


}
