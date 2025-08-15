package com.fsse2506.fsse2506backend.mapper.cartItem.entity;

import com.fsse2506.fsse2506backend.data.cartItem.enitty.CartItemEntity;
import com.fsse2506.fsse2506backend.data.product.entity.ProductEntity;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemEntityMapper {

    public CartItemEntity toCartItemEntity (ProductEntity product, UserEntity user, Integer quantity){
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setProduct(product);
        cartItemEntity.setUser(user);
        cartItemEntity.setQuantity(quantity);
        return cartItemEntity;
    }

}
