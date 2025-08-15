package com.fsse2506.fsse2506backend.service.impl;

import com.fsse2506.fsse2506backend.data.cartItem.domainObject.CartItemResponseData;
import com.fsse2506.fsse2506backend.data.cartItem.enitty.CartItemEntity;
import com.fsse2506.fsse2506backend.data.product.entity.ProductEntity;
import com.fsse2506.fsse2506backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import com.fsse2506.fsse2506backend.exception.cartItem.CartItemDeleteFailException;
import com.fsse2506.fsse2506backend.exception.cartItem.CartItemExceedStockException;
import com.fsse2506.fsse2506backend.exception.cartItem.CartItemNotFoundException;
import com.fsse2506.fsse2506backend.mapper.cartItem.CartItemDataMapper;
import com.fsse2506.fsse2506backend.mapper.cartItem.entity.CartItemEntityMapper;
import com.fsse2506.fsse2506backend.repository.CartItemRepository;
import com.fsse2506.fsse2506backend.repository.UserRepository;
import com.fsse2506.fsse2506backend.service.CartItemService;
import com.fsse2506.fsse2506backend.service.ProductService;
import com.fsse2506.fsse2506backend.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartItemServiceImpl implements CartItemService {
    private final Logger log = LoggerFactory.getLogger(CartItemServiceImpl.class);
    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;
    private final CartItemEntityMapper cartItemEntityMapper;
    private final CartItemDataMapper cartItemDataMapper;
    private final UserRepository userRepository;

    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository, CartItemEntityMapper cartItemEntityMapper, CartItemDataMapper cartItemDataMapper, UserRepository userRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
        this.cartItemEntityMapper = cartItemEntityMapper;
        this.cartItemDataMapper = cartItemDataMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        try{
            UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);
            ProductEntity productEntity = productService.getEntityByPid(pid);

            Optional<CartItemEntity> cartItemEntityOptional = cartItemRepository.findByUserAndProduct_Pid(userEntity, pid);

            if (cartItemEntityOptional.isPresent()){
                CartItemEntity cartItemEntity = cartItemEntityOptional.get();
                cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);

                if (!productService.isEnoughStock(productEntity, cartItemEntity.getQuantity())){
                    throw new CartItemExceedStockException(productEntity.getPid());
                }
            } else {
                CartItemEntity cartItemEntity = cartItemEntityMapper.toCartItemEntity(productEntity,userEntity,quantity);
                if (!productService.isEnoughStock(productEntity, cartItemEntity.getQuantity())){
                    throw new CartItemExceedStockException(productEntity.getPid());
                }
                cartItemRepository.save(cartItemEntity);
            }
        }   catch (Exception ex){
                log.warn("Put item into cart failed:{}",ex.getMessage());
                throw ex;
        }


    }

    @Override
    public List<CartItemEntity> getUserCartEntity(UserEntity userEntity){
        return cartItemRepository.findByUser(userEntity);
    }

    @Override
    public List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData){
        return cartItemDataMapper.toCartItemResponseDataList(cartItemRepository.findByUser(
                userService.getUserEntityByFirebaseUserData(firebaseUserData)
                )
        );
    }
    @Transactional
    @Override
    public void patchCartItemQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){

        try{
            UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);
            ProductEntity productEntity = productService.getEntityByPid(pid);
            CartItemEntity cartItemEntity = getEntityByUserAndPid(userEntity, pid);
            if (!productService.isEnoughStock(productEntity, quantity)){
                throw new CartItemExceedStockException(productEntity.getPid());
            }
            cartItemEntity.setQuantity(quantity);
        }catch (Exception ex){
            log.warn("Update item in cart failed: {}", ex.getMessage());
            throw ex;
        }

    }

    @Transactional
    @Override
    public void deleteCartItem(FirebaseUserData firebaseUserData, Integer pid){
        try{
            Integer result = cartItemRepository.deleteByUser_EmailAndProduct_Pid(firebaseUserData.getEmail(), pid);
            if (result == 0){
                throw  new CartItemDeleteFailException(firebaseUserData.getEmail(), pid);
            }
        }   catch (Exception ex){
            log.warn("Delete item in cart failed: {}", ex.getMessage());
            throw ex;
        }

    }


    @Transactional
    @Override
    public void cleanCartItem(FirebaseUserData firebaseUserData) {
        cartItemRepository.deleteByUser_Email(firebaseUserData.getEmail());
    }



    public CartItemEntity getEntityByUserAndPid (UserEntity user, Integer pid){
        return cartItemRepository.findByUserAndProduct_Pid(user, pid).orElseThrow(
                () -> new CartItemNotFoundException(user.getUid(), pid)
        );
    };






}
