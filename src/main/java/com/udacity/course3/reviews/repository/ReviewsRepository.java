package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
}
