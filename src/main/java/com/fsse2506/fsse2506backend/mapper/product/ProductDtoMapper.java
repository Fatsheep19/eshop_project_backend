package com.fsse2506.fsse2506backend.mapper.product;

import com.fsse2506.fsse2506backend.data.product.domainObject.ProductResponsiveData;
import com.fsse2506.fsse2506backend.data.product.dto.GetAllProductResponsiveDto;
import com.fsse2506.fsse2506backend.data.product.dto.ProductResponseDto;
import com.fsse2506.fsse2506backend.data.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2506.fsse2506backend.data.transactionProduct.dto.TransactionProductResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDtoMapper {

    public List <GetAllProductResponsiveDto> toAllProductList
            (List<ProductResponsiveData>productResponsiveDataList){

        List <GetAllProductResponsiveDto> allProductList = new ArrayList<>();
        for (ProductResponsiveData productResponsiveData : productResponsiveDataList){
        allProductList.add(toAllProductDto(productResponsiveData));
        }
        return allProductList;
    }

    public GetAllProductResponsiveDto toAllProductDto (ProductResponsiveData productResponsiveData){
        GetAllProductResponsiveDto allProductDto = new GetAllProductResponsiveDto();
        allProductDto.setPid(productResponsiveData.getPid());
        allProductDto.setName(productResponsiveData.getName());
        allProductDto.setPrice(productResponsiveData.getPrice());
        allProductDto.setImageUrl(productResponsiveData.getImageUrl());
        allProductDto.setHasStock(productResponsiveData.getStock() > 0);
        return allProductDto;
    }

    public ProductResponseDto toProductDto (ProductResponsiveData productResponseData){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(productResponseData.getName());
        productResponseDto.setPid(productResponseData.getPid());
        productResponseDto.setPrice(productResponseData.getPrice());
        productResponseDto.setImageUrl(productResponseData.getImageUrl());
        productResponseDto.setStock(productResponseData.getStock());
        productResponseDto.setDescription(productResponseData.getDescription());
        return productResponseDto;
    }

    public ProductResponseDto toProductDto (TransactionProductResponseData transactionProductResponseData){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(transactionProductResponseData.getName());
        productResponseDto.setPid(transactionProductResponseData.getPid());
        productResponseDto.setPrice(transactionProductResponseData.getPrice());
        productResponseDto.setImageUrl(transactionProductResponseData.getImageUrl());
        productResponseDto.setStock(transactionProductResponseData.getStock());
        productResponseDto.setDescription(transactionProductResponseData.getDescription());
        return productResponseDto;
    }


}
