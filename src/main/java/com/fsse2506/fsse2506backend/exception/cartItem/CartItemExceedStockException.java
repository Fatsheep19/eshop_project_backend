package com.fsse2506.fsse2506backend.exception.cartItem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class CartItemExceedStockException extends RuntimeException {
    public CartItemExceedStockException(Integer pid) {
        super("Cannot put item pid: " + pid + " into cart; cart item exceeds stock");
    }
}
