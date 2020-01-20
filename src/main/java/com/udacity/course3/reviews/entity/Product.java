package com.udacity.course3.reviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @NotBlank
    @Column(name = "product_name")
    private String productName;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<Reviews> reviewsList;

    public Product() {
    }

    public Product(Integer productId, String productName, List<Reviews> reviewsList) {
        this.productId = productId;
        this.productName = productName;
        this.reviewsList = reviewsList;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }
}
