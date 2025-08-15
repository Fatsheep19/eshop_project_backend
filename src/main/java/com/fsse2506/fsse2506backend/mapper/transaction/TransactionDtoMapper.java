package com.fsse2506.fsse2506backend.mapper.transaction;

import com.fsse2506.fsse2506backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2506.fsse2506backend.data.transaction.dto.TransactionResponseDto;
import com.fsse2506.fsse2506backend.mapper.transactionProduct.TransactionProductDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoMapper {

    private final TransactionProductDtoMapper transactionProductDtoMapper;

    public TransactionDtoMapper(TransactionProductDtoMapper transactionProductDtoMapper) {
        this.transactionProductDtoMapper = transactionProductDtoMapper;
    }


    public TransactionResponseDto toTransactionResponseDto (TransactionResponseData transactionResponseData){

        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setTid(transactionResponseData.getTid());
        transactionResponseDto.setBuyerUid(transactionResponseData.getBuyer().getUid());
        transactionResponseDto.setDateTime(transactionResponseData.getDateTime());
        transactionResponseDto.setStatus(transactionResponseData.getStatus());
        transactionResponseDto.setTotal(transactionResponseData.getTotal());
        transactionResponseDto.setProducts(transactionProductDtoMapper.toTransactionProductResponseDtoList(transactionResponseData.getTransactionProducts()));
        return transactionResponseDto;
    }
}
