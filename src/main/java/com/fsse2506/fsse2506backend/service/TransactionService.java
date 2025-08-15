package com.fsse2506.fsse2506backend.service;

import com.fsse2506.fsse2506backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2506.fsse2506backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import jakarta.transaction.Transactional;

public interface TransactionService {
    @Transactional
    TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid);

    @Transactional
    void updateTransactionStatus(FirebaseUserData firebaseUserData, Integer tid);

    @Transactional
    TransactionResponseData successTransaction(FirebaseUserData firebaseUserData, Integer tid);
}
