package com.udacity.course3.reviews.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Integer commentsId;

    @NotBlank
    @Column(name = "comments_content")
    private String commentsContent;

    @ManyToOne
    @JoinColumn(name = "reviews_id")
    private Reviews reviews;

    public Comments() {
    }

    public Comments(Integer commentsId, String commentsContent) {
        this.commentsId = commentsId;
        this.commentsContent = commentsContent;
    }

    public Integer getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
    }

    public String getCommentsContent() {
        return commentsContent;
    }

    public void setCommentsContent(String commentsContent) {
        this.commentsContent = commentsContent;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }
}