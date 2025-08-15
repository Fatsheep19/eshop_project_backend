package com.fsse2506.fsse2506backend.service.impl;


import com.fsse2506.fsse2506backend.data.product.domainObject.ProductResponsiveData;
import com.fsse2506.fsse2506backend.data.product.entity.ProductEntity;
import com.fsse2506.fsse2506backend.mapper.product.ProductDataMapper;
import com.fsse2506.fsse2506backend.mapper.product.ProductNotFound;
import com.fsse2506.fsse2506backend.repository.ProductRepository;
import com.fsse2506.fsse2506backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;
    private  final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository, ProductDataMapper productDataMapper) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
    }

    @Override
    public List<ProductResponsiveData> getAllProduct(){

        return productDataMapper.toProductResponsiveDataList(
                (List<ProductEntity>)productRepository.findAll()
        );
    }

    @Override
    public ProductResponsiveData getProductResponsiveData(Integer pid){
        try {
            return productDataMapper.toProductResponsiveData(
                    getEntityByPid(pid));
        }
        catch (Exception ex){
            logger.warn("Get product by pid failed: {}", ex.getMessage());
            throw ex;
        }
    }
    @Override
    public ProductEntity getEntityByPid(Integer pid){
        return productRepository.findById(pid).orElseThrow(() -> new ProductNotFound(pid));
    }
    @Override
    public boolean isEnoughStock(ProductEntity productEntity, Integer quantity){
        return productEntity.getStock() > 0 && productEntity.getStock()>= quantity;
    }

}
