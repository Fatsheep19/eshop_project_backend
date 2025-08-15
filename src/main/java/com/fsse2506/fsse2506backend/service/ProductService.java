package com.fsse2506.fsse2506backend.service;

import com.fsse2506.fsse2506backend.data.product.domainObject.ProductResponsiveData;
import com.fsse2506.fsse2506backend.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductResponsiveData> getAllProduct();

    ProductResponsiveData getProductResponsiveData(Integer pId);

    ProductEntity getEntityByPid(Integer pId);

    boolean isEnoughStock(ProductEntity productEntity, Integer quantity);
}
