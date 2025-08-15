package com.fsse2506.fsse2506backend.exception.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemDeleteFailException extends RuntimeException {
    public CartItemDeleteFailException(String email, Integer pid) {
        super("Cannot find Cart Item: email =" + email + " , pid = " + pid);
    }
}
