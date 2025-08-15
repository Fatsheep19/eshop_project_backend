package com.fsse2506.fsse2506backend.data.transactionProduct.dto;

import com.fsse2506.fsse2506backend.data.product.dto.ProductResponseDto;

import java.math.BigDecimal;

public class TransactionProductResponseDto {

    private Integer tpid;
    private ProductResponseDto product;
    private BigDecimal subtotal;
    private Integer quantity;

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
