package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.ReviewsMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewsRepositoryMongo extends MongoRepository<ReviewsMongo, String> {
    List<ReviewsMongo> findByProductId(int productId);
    Optional<ReviewsMongo> findByReviewsId(int reviewsId);
}