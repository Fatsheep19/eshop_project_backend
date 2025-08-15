package com.fsse2506.fsse2506backend.service;

import com.fsse2506.fsse2506backend.data.cartItem.domainObject.CartItemResponseData;
import com.fsse2506.fsse2506backend.data.cartItem.enitty.CartItemEntity;
import com.fsse2506.fsse2506backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {
    void putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);


    List<CartItemEntity> getUserCartEntity(UserEntity userEntity);

    List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData);

    @Transactional
    void patchCartItemQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    @Transactional
    void deleteCartItem(FirebaseUserData firebaseUserData, Integer pid);

    @Transactional
    void cleanCartItem(FirebaseUserData firebaseUserData);
}
