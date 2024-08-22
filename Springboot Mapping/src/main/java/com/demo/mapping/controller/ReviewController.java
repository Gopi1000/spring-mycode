package com.demo.mapping.controller;

import com.demo.mapping.model.Review;
import com.demo.mapping.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")

public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<Review> addABook(@RequestBody Review review) {
        return new ResponseEntity<Review>(reviewService.saveOrUpdateABook(review), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public Iterable<Review> getAllBookings(){
        return reviewService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getBookingById(@PathVariable Long reviewId) {
        return new ResponseEntity<Review>(reviewService.findReviewById(reviewId), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update/{reviewId}")
   	public void update(@RequestBody Review c, @PathVariable Long reviewId) {
    	reviewService.update(reviewId, c);
   	}
    
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{reviewId}")
   	public void deletereview(@PathVariable Long reviewId) {
    	reviewService.delete(reviewId);
   	}
}
