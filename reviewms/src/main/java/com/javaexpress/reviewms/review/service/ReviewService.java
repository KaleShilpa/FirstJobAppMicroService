package com.javaexpress.reviewms.review.service;

import java.util.List;

import com.javaexpress.reviewms.review.bean.Review;

public interface ReviewService {
	
	List<Review> getAllReviews(Long companyId);
	
	boolean addReview(Long companyId, Review review);
	
	Review getReview(Long reviewId);

	boolean updateReview(Long reviewId, Review review);
	
	boolean deleteReview(Long reviewId);
}
