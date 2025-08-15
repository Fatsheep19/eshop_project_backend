package com.fsse2506.fsse2506backend.mapper.transactionProduct;

import com.fsse2506.fsse2506backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2506.fsse2506backend.data.transaction.dto.TransactionResponseDto;
import com.fsse2506.fsse2506backend.data.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2506.fsse2506backend.data.transactionProduct.dto.TransactionProductResponseDto;
import com.fsse2506.fsse2506backend.mapper.product.ProductDtoMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductDtoMapper {

    private final ProductDtoMapper productDtoMapper;

    public TransactionProductDtoMapper(ProductDtoMapper productDtoMapper) {
        this.productDtoMapper = productDtoMapper;
    }

    public TransactionProductResponseDto toTransactionProductResponseDto (TransactionProductResponseData transactionProductResponseData){
        TransactionProductResponseDto transactionProductResponseDto = new TransactionProductResponseDto();
        transactionProductResponseDto.setProduct(productDtoMapper.toProductDto(transactionProductResponseData));
        transactionProductResponseDto.setTpid(transactionProductResponseData.getTpid());
        transactionProductResponseDto.setQuantity(transactionProductResponseData.getQuantity());
        transactionProductResponseDto.setSubtotal(transactionProductResponseData.getPrice().multiply(new BigDecimal(transactionProductResponseData.getQuantity())));

        return transactionProductResponseDto;
    }

    public List<TransactionProductResponseDto> toTransactionProductResponseDtoList (List <TransactionProductResponseData> transactionProductResponseDataList){

       List  <TransactionProductResponseDto> transactionProductResponseDtoList = new ArrayList<>();
       for (TransactionProductResponseData responseData : transactionProductResponseDataList){
           transactionProductResponseDtoList.add(toTransactionProductResponseDto(responseData));
       }

       return transactionProductResponseDtoList;
    }
}
