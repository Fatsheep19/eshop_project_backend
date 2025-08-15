package com.fsse2506.fsse2506backend.mapper.cartItem;

import com.fsse2506.fsse2506backend.data.cartItem.domainObject.CartItemResponseData;
import com.fsse2506.fsse2506backend.data.cartItem.enitty.CartItemEntity;
import com.fsse2506.fsse2506backend.mapper.product.ProductDataMapper;
import com.fsse2506.fsse2506backend.mapper.user.UserDataMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDataMapper {

    private final ProductDataMapper productDataMapper;
    private final UserDataMapper userDataMapper;

    public CartItemDataMapper(ProductDataMapper productDataMapper, UserDataMapper userDataMapper) {
        this.productDataMapper = productDataMapper;
        this.userDataMapper = userDataMapper;
    }

    public CartItemResponseData toCartItemResponseData (CartItemEntity cartItemEntity){
        CartItemResponseData cartItemResponseData = new CartItemResponseData();
        cartItemResponseData.setCid(cartItemEntity.getCid());
        cartItemResponseData.setQuantity(cartItemEntity.getQuantity());
        cartItemResponseData.setProduct(productDataMapper.toProductResponsiveData(cartItemEntity.getProduct()));
        cartItemResponseData.setUser(userDataMapper.toUserResponseData(cartItemEntity.getUser()));
        return cartItemResponseData;
    }

    public List<CartItemResponseData> toCartItemResponseDataList (List<CartItemEntity> cartItemEntityList){
        List <CartItemResponseData> cartItemResponseDataList = new ArrayList<>();
        for (CartItemEntity cartItemEntity :cartItemEntityList){
            cartItemResponseDataList.add(toCartItemResponseData(cartItemEntity));
        }

        return cartItemResponseDataList;
    }
}
