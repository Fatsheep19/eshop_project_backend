package com.fsse2506.fsse2506backend.service.impl;

import com.fsse2506.fsse2506backend.data.cartItem.enitty.CartItemEntity;
import com.fsse2506.fsse2506backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2506.fsse2506backend.data.transaction.entity.TransactionEntity;
import com.fsse2506.fsse2506backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2506.fsse2506backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import com.fsse2506.fsse2506backend.enumeration.TransactionStatus;
import com.fsse2506.fsse2506backend.exception.transaction.*;
import com.fsse2506.fsse2506backend.mapper.transaction.TransactionDataMapper;
import com.fsse2506.fsse2506backend.mapper.transaction.entity.TransactionEntityMapper;
import com.fsse2506.fsse2506backend.mapper.transactionProduct.entity.TransactionProductEntityMapper;
import com.fsse2506.fsse2506backend.mapper.user.UserEntityMapper;
import com.fsse2506.fsse2506backend.repository.TransactionRepository;
import com.fsse2506.fsse2506backend.service.CartItemService;
import com.fsse2506.fsse2506backend.service.TransactionProductService;
import com.fsse2506.fsse2506backend.service.TransactionService;
import com.fsse2506.fsse2506backend.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    private final UserEntityMapper userEntityMapper;
    private final CartItemService cartItemService;
    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final TransactionEntityMapper transactionEntityMapper;
    private final TransactionRepository transactionRepository;
    private final TransactionProductEntityMapper transactionProductEntityMapper;
    private final TransactionProductService transactionProductService;
    private final TransactionDataMapper transactionDataMapper;
    private final UserService userService;

    public TransactionServiceImpl(UserEntityMapper userEntityMapper, CartItemService cartItemService, TransactionEntityMapper transactionEntityMapper, TransactionRepository transactionRepository, TransactionProductEntityMapper transactionProductEntityMapper, TransactionProductService transactionProductService, TransactionDataMapper transactionDataMapper, UserService userService) {
        this.userEntityMapper = userEntityMapper;
        this.cartItemService = cartItemService;
        this.transactionEntityMapper = transactionEntityMapper;
        this.transactionRepository = transactionRepository;
        this.transactionProductEntityMapper = transactionProductEntityMapper;
        this.transactionProductService = transactionProductService;
        this.transactionDataMapper = transactionDataMapper;
        this.userService = userService;
    }


    @Transactional
    @Override
    public TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData) {
        try {
            UserEntity user = userService.getUserEntityByFirebaseUserData(firebaseUserData);
            List<CartItemEntity> cartItemEntityList = cartItemService.getUserCartEntity(user);

            if (cartItemEntityList.isEmpty()) {
                throw new TransactionCartEmptyException(user.getUid());
            }

            TransactionEntity transactionEntity = transactionEntityMapper.toTransactionEntityMapper(user);
            // still not update the transaction product list
            transactionEntity = transactionRepository.save(transactionEntity);

            for (CartItemEntity cartItemEntity : cartItemEntityList) {
                TransactionProductEntity transactionProductEntity = transactionProductService.createTransactionProduct(transactionEntity, cartItemEntity);
                transactionEntity.getTransactionProducts().add(transactionProductEntity);
                transactionEntity.setTotal(transactionEntity.getTotal().add(
                                transactionProductEntity.getPrice().multiply(
                                        new BigDecimal(transactionProductEntity.getQuantity())
                                )
                        )
                );
            }
            return transactionDataMapper.toTransactionResponseData(transactionEntity);


        } catch (Exception ex) {
            log.warn("Failed to prepare the transaction: {}", ex.getMessage());
            throw ex;

        }


    }

    @Override
    public TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid) {
        try {

            UserEntity buyer = userService.getUserEntityByFirebaseUserData(firebaseUserData);
            TransactionEntity transactionEntity = transactionRepository.findByBuyerAndTid(buyer, tid).orElseThrow(
                    () -> new TransactionNotFound(tid));


            return transactionDataMapper.toTransactionResponseData(transactionEntity);
        } catch (Exception ex) {
            log.warn("Failed to find the transaction: {}", ex.getMessage());
            throw ex;
        }

    }

    @Transactional
    @Override
    public void updateTransactionStatus(FirebaseUserData firebaseUserData, Integer tid) {
        try {

            UserEntity buyer = userService.getUserEntityByFirebaseUserData(firebaseUserData);
            TransactionEntity transactionEntity = transactionRepository.findByBuyerAndTid(buyer, tid).orElseThrow(
                    () -> new TransactionNotFound(tid));

            if (transactionEntity.getStatus() == TransactionStatus.PROCESSING) {
                throw new TransactionAlreadyProcessing(tid);
            }
            if (transactionEntity.getStatus() == TransactionStatus.SUCCESS) {
                throw new TransactionPaymentAlreadyCompleted(tid);
            }
            transactionEntity.setStatus(TransactionStatus.PROCESSING);

        } catch (Exception ex) {
            if (ex instanceof TransactionNotFound) {
                log.warn("Failed to find the transaction: {}", ex.getMessage());

                if (ex instanceof TransactionPaymentAlreadyCompleted) {
                    log.warn("Transaction payment already completed: {}", ex.getMessage());

                } else {
                    log.warn("Failed to update the transaction status + {}", ex.getMessage());

                }
            }
            throw ex;
        }
    }


    @Transactional
    @Override
    public TransactionResponseData successTransaction(FirebaseUserData firebaseUserData, Integer tid) {
        try {

            UserEntity buyer = userService.getUserEntityByFirebaseUserData(firebaseUserData);
            TransactionEntity transactionEntity = transactionRepository.findByBuyerAndTid(buyer, tid).orElseThrow(
                    () -> new TransactionNotFound(tid));

            if (transactionEntity.getStatus() == TransactionStatus.PREPARE) {
                throw new TransactionPaymentFailed(tid);
            } else if (transactionEntity.getStatus() == TransactionStatus.SUCCESS) {
                throw new TransactionPaymentAlreadyCompleted(tid);
            }

            transactionEntity.setStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transactionEntity);
            cartItemService.cleanCartItem(firebaseUserData);
            return transactionDataMapper.toTransactionResponseData(transactionEntity);

        } catch (Exception ex) {
            if (ex instanceof TransactionPaymentFailed) {
                log.warn("Failed to find the transaction: {}", ex.getMessage());
            }
            if (ex instanceof TransactionPaymentAlreadyCompleted) {
                log.warn("Transaction payment already completed: {}", ex.getMessage());
            }
            if (ex instanceof TransactionNotFound) {
                log.warn("Failed to find the transaction: {}", ex.getMessage());
            } else if (ex instanceof TransactionAlreadyProcessing) {
                log.warn("Failed to update the transaction status + {}", ex.getMessage());
            } else {
                log.warn("Failed to make the payment + {}", ex.getMessage());
            }
            throw ex;
        }
    }
}


