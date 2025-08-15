package com.fsse2506.fsse2506backend.mapper.product;

import com.fsse2506.fsse2506backend.data.product.entity.ProductEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFound extends RuntimeException {
    public ProductNotFound(Integer pId) {
    super("Product id " + pId + " is not found");
}
}
