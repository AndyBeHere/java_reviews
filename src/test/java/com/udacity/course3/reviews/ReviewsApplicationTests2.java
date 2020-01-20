package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.entity.ReviewsMongo;
import com.udacity.course3.reviews.repository.CommentsRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepositoryMongo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewsApplicationTests2 {

    @Autowired
    private ReviewsRepositoryMongo reviewsRepositoryMongo;


    @Test
    public void testReviewsMongo() {
        ReviewsMongo reviewsMongo = new ReviewsMongo();
        reviewsMongo.setProductId(1);
        reviewsMongo.setReview("This is a test for apple.");

        reviewsRepositoryMongo.save(reviewsMongo);

        List reviewsList = reviewsRepositoryMongo.findByProductId(1);
        System.out.println(reviewsList);

    }


}
