package com.fsse2506.fsse2506backend.repository;

import com.fsse2506.fsse2506backend.data.cartItem.enitty.CartItemEntity;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository <CartItemEntity, Integer> {

    boolean existsByUserAndProduct_Pid(UserEntity user, Integer productPid);

    Optional<CartItemEntity> findByUserAndProduct_Pid(UserEntity user, Integer productPid);

    List<CartItemEntity> findByUser(UserEntity user);

    Integer deleteByUser_EmailAndProduct_Pid(String userEmail,Integer productPid);

    UserEntity user(UserEntity user);

    Integer deleteByUser_Email(String email);
}
