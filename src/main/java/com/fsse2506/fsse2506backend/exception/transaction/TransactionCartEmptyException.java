package com.fsse2506.fsse2506backend.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionCartEmptyException extends RuntimeException{
    public TransactionCartEmptyException( Integer uid) {
        super("Cannot do transaction for uid: " + uid + " ; transaction cart is empty");
    }

}
