package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.ReviewsMongo;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private ReviewsRepositoryMongo reviewsRepositoryMongo;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId,
                                                    @RequestBody @Valid Reviews reviews) {

        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()){
            /*
            As the last reviewer pointed out, I have saved the reviews using reviewsRepository first,
            so the return variable reviews1 gets a valid id.
            Then I set the id using reviews1 in reviewsMongo as requested.

            Is there something else I'am missing?
             */

            // save to mysql
            reviews.setProduct(product.get());
            Reviews reviews1 = reviewsRepository.save(reviews);

            // save to mongo
            ReviewsMongo reviewsMongo = new ReviewsMongo();
            reviewsMongo.setProductId(productId);
            reviewsMongo.setReview(reviews.getReviewsContent());
            reviewsMongo.setReviewsId(reviews1.getReviewsId());
            reviewsRepositoryMongo.save(reviewsMongo);

            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

     /**
     *      * Lists reviews by product.
     *      *
     *      * @param productId The id of the product.
     *      * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()){

            List reviewsList = reviewsRepositoryMongo.findByProductId(productId);
            return new ResponseEntity<List<?>>(reviewsList, HttpStatus.OK);

            /* this is for midterm
            List reviewsList = product.get().getReviewsList();
            return new ResponseEntity<List<?>>(reviewsList, HttpStatus.OK);
            */
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}