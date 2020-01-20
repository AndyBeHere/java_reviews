package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.CommentModel;
import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.entity.ReviewsMongo;
import com.udacity.course3.reviews.repository.CommentsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    private ReviewsRepositoryMongo reviewsRepositoryMongo;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
                                                    @RequestBody @Valid Comments comments) {

        Optional<Reviews> reviews = reviewsRepository.findById(reviewId);
        Optional<ReviewsMongo> reviewsMongo = reviewsRepositoryMongo.findByReviewsId(reviewId);

        if (reviews.isPresent() && reviewsMongo.isPresent()){

            // save to mongo
            CommentModel commentModel = new CommentModel();
            commentModel.setContent(comments.getCommentsContent());

            List<CommentModel> commnetsList = reviewsMongo.get().getComments();
            commnetsList.add(commentModel);

            reviewsMongo.get().setComments(commnetsList);
            reviewsRepositoryMongo.save(reviewsMongo.get());

            // save to sql
            comments.setReviews(reviews.get());
            commentsRepository.save(comments);

            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Optional<Reviews> reviews = reviewsRepository.findById(reviewId);
        Optional<ReviewsMongo> reviewsMongo = reviewsRepositoryMongo.findByReviewsId(reviewId);

        if (reviews.isPresent() && reviewsMongo.isPresent()){
            List commentsList = reviewsMongo.get().getComments();
            // List commentsList = reviews.get().getCommentsList();
            return commentsList;
        } else {
            return null;
        }
    }
}