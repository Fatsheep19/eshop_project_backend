package com.fsse2506.fsse2506backend.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2506.fsse2506backend.data.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2506.fsse2506backend.data.transactionProduct.dto.TransactionProductResponseDto;
import com.fsse2506.fsse2506backend.data.user.domainObject.response.UserResponseData;
import com.fsse2506.fsse2506backend.enumeration.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {

    private Integer tid;
    private Integer buyerUid;
    @JsonFormat(pattern = "yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    private TransactionStatus status;
    private BigDecimal total;
    @JsonProperty(value = "items")
    private List<TransactionProductResponseDto> products = new ArrayList<>();


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
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

    public List<TransactionProductResponseDto> getProducts() {
        return products;
    }

    public void setProducts(List<TransactionProductResponseDto> products) {
        this.products = products;
    }
}
