package com.fsse2506.fsse2506backend.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionAlreadyProcessing extends RuntimeException {
    public TransactionAlreadyProcessing(Integer tid) {
        super("Cannot update the transaction status. The transaction id: " + tid + " is already processing");
    }
}
