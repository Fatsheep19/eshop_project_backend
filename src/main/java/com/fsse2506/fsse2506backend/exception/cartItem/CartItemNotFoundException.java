package com.fsse2506.fsse2506backend.exception.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Integer uid, Integer pid) {
        super("Cart item not found: uid = " + uid + ", pid = " + pid);
    }
}
