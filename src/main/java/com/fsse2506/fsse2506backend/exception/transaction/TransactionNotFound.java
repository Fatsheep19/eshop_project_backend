package com.fsse2506.fsse2506backend.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFound extends RuntimeException {
    public TransactionNotFound(Integer tid) {
        super("Cannot find the transaction with id:" + tid);
    }
}
