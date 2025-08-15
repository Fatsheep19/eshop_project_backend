package com.fsse2506.fsse2506backend.data.product.dto;

import com.fsse2506.fsse2506backend.data.product.domainObject.ProductResponsiveData;

import java.math.BigDecimal;

public class GetAllProductResponsiveDto {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Boolean hasStock;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }
}
