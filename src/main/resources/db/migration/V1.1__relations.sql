
ALTER TABLE reviews
ADD CONSTRAINT fk_reviews FOREIGN KEY(product_id) REFERENCES product(product_id);

ALTER TABLE comments
ADD CONSTRAINT fk_comments FOREIGN KEY(reviews_id) REFERENCES reviews(reviews_id);
