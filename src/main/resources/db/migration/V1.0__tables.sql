CREATE TABLE product (
    product_id bigint(20) NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (product_id)
);

CREATE TABLE reviews (
    reviews_id bigint(20) NOT NULL AUTO_INCREMENT,
    reviews_content VARCHAR(1000) NOT NULL,
    product_id bigint(20),
    PRIMARY KEY (reviews_id)
);

CREATE TABLE comments (
    comments_id bigint(20) NOT NULL AUTO_INCREMENT,
    comments_content VARCHAR(1000) NOT NULL,
    reviews_id bigint(20),
    PRIMARY KEY (comments_id)
);