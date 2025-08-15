package com.fsse2506.fsse2506backend.data.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

@Entity
@Table (name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column (nullable = false)
    private String name;

    private String description;
    private String imageUrl;

    @Column (nullable = false)
    @Min(0)
    private BigDecimal price;

    @Column (nullable = false)
    @Min(0)
    private Integer stock;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
