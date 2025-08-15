package com.fsse2506.fsse2506backend.controller.cartItem;

import com.fsse2506.fsse2506backend.data.cartItem.domainObject.CartItemResponseData;
import com.fsse2506.fsse2506backend.data.cartItem.dto.CartItemResponseDto;
import com.fsse2506.fsse2506backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2506.fsse2506backend.mapper.cartItem.dto.CartItemDtoMapper;
import com.fsse2506.fsse2506backend.mapper.user.UserDataMapper;
import com.fsse2506.fsse2506backend.service.CartItemService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/items")
public class CartItemController {

    private final UserDataMapper userDataMapper;
    private final CartItemService cartItemService;
    private final CartItemDtoMapper cartItemDtoMapper;

    public CartItemController(UserDataMapper userDataMapper, CartItemService cartItemService, CartItemDtoMapper cartItemDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.cartItemService = cartItemService;
        this.cartItemDtoMapper = cartItemDtoMapper;
    }

    @PutMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putCartItem(@PathVariable Integer pid ,
                            @Positive @PathVariable Integer quantity,
                            @AuthenticationPrincipal Jwt jwt){

        cartItemService.putCartItem(userDataMapper.toFirebaseUserData(jwt),pid,quantity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List <CartItemResponseDto> getUserCart (@AuthenticationPrincipal Jwt jwt){
        return cartItemDtoMapper.toCartItemResponseDtoList(cartItemService.getUserCart(
                userDataMapper.toFirebaseUserData(jwt)
        )
        );
    }

    @PatchMapping ("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCartQuantity  (@PathVariable Integer pid,
                                    @Positive @PathVariable Integer quantity,
                                    @AuthenticationPrincipal Jwt jwt)
    {
        cartItemService.patchCartItemQuantity(userDataMapper.toFirebaseUserData(jwt),pid, quantity);
    }

    @DeleteMapping ("/{pid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCartItem (@PathVariable Integer pid, @AuthenticationPrincipal Jwt jwt){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.deleteCartItem(firebaseUserData,pid);
    }


}
