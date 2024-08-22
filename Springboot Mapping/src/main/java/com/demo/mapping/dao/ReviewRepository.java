package com.demo.mapping.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.mapping.model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Review getByReviewId(Long id);
}
