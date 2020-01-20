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
@DataJpaTest
public class ReviewsApplicationTests {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private CommentsRepository commentsRepository;


    @Test
    public void testCreateProduct() {

        Product product = new Product();
        product.setProductName("apple");

        entityManager.persist(product);

        Product actual = productRepository.findByProductName("apple");
        assertThat(actual).isNotNull();
        assertEquals(product.getProductId(), actual.getProductId());
    }

    @Test
    public void testProductFindById() {

        Product product = new Product();
        product.setProductName("apple");

        entityManager.persist(product);

        Optional<Product> actual = productRepository.findById(product.getProductId());

        assertThat(actual.isPresent());

        assertThat(actual);
        assertEquals(product.getProductName(), actual.get().getProductName());
    }

    @Test
    public void testListProducts() {

        Product product1 = new Product();
        product1.setProductName("apple");
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setProductName("google");
        entityManager.persist(product2);

        List<Product> productList = productRepository.findAll();
        assertEquals(product1.getProductName(), productList.get(0).getProductName());
    }

    @Test
    public void testCreateReviews() {
        Reviews reviews = new Reviews();
        Product product = new Product();
        product.setProductName("apple");

        reviews.setReviewsContent("This is reviews.");
        reviews.setProduct(product);

        entityManager.persist(reviews);
        Optional<Reviews> actual = reviewsRepository.findById(reviews.getReviewsId());

        assertEquals(actual.get().getReviewsContent(), reviews.getReviewsContent());

    }

    @Test
    public void testCreateComments() {
        Comments comments = new Comments();
        comments.setCommentsContent("This is comments");

        Reviews reviews = new Reviews();
        reviews.setReviewsContent("This is review.");

        Product product = new Product();
        product.setProductName("apple");

        reviews.setProduct(product);
        comments.setReviews(reviews);

        entityManager.persist(comments);
        Optional<Comments> actual = commentsRepository.findById(comments.getCommentsId());

        assertEquals(actual.get().getCommentsContent(), comments.getCommentsContent());

    }




}
