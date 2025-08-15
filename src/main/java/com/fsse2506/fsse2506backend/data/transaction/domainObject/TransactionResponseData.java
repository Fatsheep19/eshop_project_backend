package com.fsse2506.fsse2506backend.data.transaction.domainObject;

import com.fsse2506.fsse2506backend.data.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2506.fsse2506backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2506.fsse2506backend.data.user.domainObject.response.UserResponseData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import com.fsse2506.fsse2506backend.enumeration.TransactionStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseData {


    private Integer tid;
    private UserResponseData buyer;
    private LocalDateTime dateTime;
    private TransactionStatus status;
    private BigDecimal total;
    private List<TransactionProductResponseData> transactionProducts = new ArrayList<>();


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserResponseData getBuyer() {
        return buyer;
    }

    public void setBuyer(UserResponseData buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductResponseData> getTransactionProducts() {
        return transactionProducts;
    }

    public void setTransactionProducts(List<TransactionProductResponseData> transactionProducts) {
        this.transactionProducts = transactionProducts;
    }
}
