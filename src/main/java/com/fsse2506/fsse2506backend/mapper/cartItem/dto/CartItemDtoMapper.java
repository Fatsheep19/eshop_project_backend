package com.fsse2506.fsse2506backend.mapper.cartItem.dto;

import com.fsse2506.fsse2506backend.data.cartItem.domainObject.CartItemResponseData;
import com.fsse2506.fsse2506backend.data.cartItem.dto.CartItemResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDtoMapper {

    public CartItemResponseDto toCartItemResponseDto (CartItemResponseData cartItemResponseData){
        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
        cartItemResponseDto.setCartQuantity(cartItemResponseData.getQuantity());
        cartItemResponseDto.setName(cartItemResponseData.getProduct().getName());
        cartItemResponseDto.setPid(cartItemResponseData.getProduct().getPid());
        cartItemResponseDto.setPrice(cartItemResponseData.getProduct().getPrice());
        cartItemResponseDto.setImageUrl(cartItemResponseData.getProduct().getImageUrl());
        cartItemResponseDto.setStock(cartItemResponseData.getProduct().getStock());
        return cartItemResponseDto;
    }

    public List<CartItemResponseDto> toCartItemResponseDtoList (List<CartItemResponseData> cartItemResponseDataList){
        List <CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();
        for (CartItemResponseData cartItemResponseData :cartItemResponseDataList){
            cartItemResponseDtoList.add(toCartItemResponseDto(cartItemResponseData));
        }
        return cartItemResponseDtoList;
    }
}
