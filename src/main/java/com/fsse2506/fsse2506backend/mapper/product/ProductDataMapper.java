package com.fsse2506.fsse2506backend.mapper.product;

import com.fsse2506.fsse2506backend.data.product.domainObject.ProductResponsiveData;
import com.fsse2506.fsse2506backend.data.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDataMapper {

    public List<ProductResponsiveData>  toProductResponsiveDataList (List<ProductEntity> productEntityList){
        List <ProductResponsiveData> productResponsiveDataList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList){
            productResponsiveDataList.add(toProductResponsiveData(productEntity));
        }
        return productResponsiveDataList;
    }



    public ProductResponsiveData toProductResponsiveData (ProductEntity productEntity){
        ProductResponsiveData productResponsiveData = new ProductResponsiveData();
        productResponsiveData.setPid(productEntity.getPid());
        productResponsiveData.setName(productEntity.getName());
        productResponsiveData.setDescription(productEntity.getDescription());
        productResponsiveData.setPrice(productEntity.getPrice());
        productResponsiveData.setImageUrl(productEntity.getImageUrl());
        productResponsiveData.setStock(productEntity.getStock());
        return productResponsiveData;
    }


}
