package com.udacity.course3.reviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Product;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_id")
    private Integer reviewsId;

    @Column(name = "reviews_content")
    @NotBlank
    private String reviewsContent;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;

    @JsonIgnore
    @OneToMany(mappedBy = "reviews", cascade = CascadeType.PERSIST)
    private List<Comments> commentsList;

    public Reviews() {
    }

    public Reviews(Integer reviewsId, String reviewsContent) {
        this.reviewsId = reviewsId;
        this.reviewsContent = reviewsContent;
    }

    public Integer getReviewsId() {
        return reviewsId;
    }

    public void setReviewsId(Integer reviewsId) {
        this.reviewsId = reviewsId;
    }

    public String getReviewsContent() {
        return reviewsContent;
    }

    public void setReviewsContent(String reviewsContent) {
        this.reviewsContent = reviewsContent;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setReviewsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }
}
