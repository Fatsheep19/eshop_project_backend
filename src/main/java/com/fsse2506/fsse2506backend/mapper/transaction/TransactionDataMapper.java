package com.fsse2506.fsse2506backend.mapper.transaction;

import com.fsse2506.fsse2506backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2506.fsse2506backend.data.transaction.entity.TransactionEntity;
import com.fsse2506.fsse2506backend.mapper.transactionProduct.TransactionProductDataMapper;
import com.fsse2506.fsse2506backend.mapper.user.UserDataMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionDataMapper {

    private final TransactionProductDataMapper transactionProductDataMapper;
    private final UserDataMapper userDataMapper;

    public TransactionDataMapper(TransactionProductDataMapper transactionProductDataMapper, UserDataMapper userDataMapper) {
        this.transactionProductDataMapper = transactionProductDataMapper;
        this.userDataMapper = userDataMapper;
    }

    public TransactionResponseData toTransactionResponseData (TransactionEntity transactionEntity){
        TransactionResponseData transactionResponseData = new TransactionResponseData();
        transactionResponseData.setBuyer(userDataMapper.toUserResponseData(transactionEntity.getBuyer()));
        transactionResponseData.setTransactionProducts(transactionProductDataMapper.toTransactionProductResponseDataList(transactionEntity.getTransactionProducts()));
        transactionResponseData.setStatus(transactionEntity.getStatus());
        transactionResponseData.setTid(transactionEntity.getTid());
        transactionResponseData.setTotal(transactionEntity.getTotal());
        transactionResponseData.setDateTime(transactionEntity.getDateTime());

        return transactionResponseData;
    }
}
