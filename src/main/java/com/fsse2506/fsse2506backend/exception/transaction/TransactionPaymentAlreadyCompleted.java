package com.fsse2506.fsse2506backend.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionPaymentAlreadyCompleted extends RuntimeException {
    public TransactionPaymentAlreadyCompleted(Integer tid) {
        super("Cannot make the purchase. The transaction id: " + tid + " is already completed");
    }
}
