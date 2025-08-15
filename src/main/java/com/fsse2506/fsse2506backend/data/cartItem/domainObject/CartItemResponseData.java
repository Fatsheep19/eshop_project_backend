package com.fsse2506.fsse2506backend.data.cartItem.domainObject;

import com.fsse2506.fsse2506backend.data.product.domainObject.ProductResponsiveData;
import com.fsse2506.fsse2506backend.data.product.entity.ProductEntity;
import com.fsse2506.fsse2506backend.data.user.domainObject.response.UserResponseData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import jakarta.persistence.*;

public class CartItemResponseData {

    private Integer cid;
    private ProductResponsiveData product;
    private UserResponseData user;
    private Integer quantity;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductResponsiveData getProduct() {
        return product;
    }

    public void setProduct(ProductResponsiveData product) {
        this.product = product;
    }

    public UserResponseData getUser() {
        return user;
    }

    public void setUser(UserResponseData user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
