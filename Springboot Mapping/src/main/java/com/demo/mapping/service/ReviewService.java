package com.demo.mapping.service;

import com.demo.mapping.dao.ReviewRepository;
import com.demo.mapping.model.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public Review saveOrUpdateABook(Review review) {
        return reviewRepository.save(review);
    }

    public Review findReviewById(Long bookId) {
        return reviewRepository.getByReviewId(bookId);
    }

    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

	public void delete(Long reviewId) {
		reviewRepository.deleteById(reviewId);
		
	}

	public void update(Long reviewId, Review c) {
		reviewRepository.save(c);
		
	}
}
