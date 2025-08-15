package com.fsse2506.fsse2506backend.mapper.transactionProduct;

import com.fsse2506.fsse2506backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2506.fsse2506backend.data.transaction.entity.TransactionEntity;
import com.fsse2506.fsse2506backend.data.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2506.fsse2506backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2506.fsse2506backend.mapper.transaction.TransactionDataMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductDataMapper {


    public TransactionProductResponseData toTransactionProductResponseData (TransactionProductEntity transactionProductEntity){

        TransactionProductResponseData transactionProductResponseData = new TransactionProductResponseData();
        transactionProductResponseData.setDescription(transactionProductEntity.getDescription());
        transactionProductResponseData.setName(transactionProductEntity.getName());
        transactionProductResponseData.setPid(transactionProductEntity.getPid());
        transactionProductResponseData.setPrice(transactionProductEntity.getPrice());
        transactionProductResponseData.setQuantity(transactionProductEntity.getQuantity());
        transactionProductResponseData.setStock(transactionProductEntity.getStock());
        transactionProductResponseData.setTpid(transactionProductEntity.getTpid());
        transactionProductResponseData.setImageUrl(transactionProductEntity.getImageUrl());

        return transactionProductResponseData;
    }


    public List<TransactionProductResponseData> toTransactionProductResponseDataList (List <TransactionProductEntity> transactionProductEntityList){

        List <TransactionProductResponseData> transactionProductResponseDataList = new ArrayList<>();
        for (TransactionProductEntity transactionProductEntity : transactionProductEntityList){
            transactionProductResponseDataList.add(toTransactionProductResponseData(transactionProductEntity));
        }

        return transactionProductResponseDataList;
    }

}
