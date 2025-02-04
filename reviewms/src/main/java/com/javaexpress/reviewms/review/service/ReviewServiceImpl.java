package com.javaexpress.reviewms.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.reviewms.review.bean.Review;
import com.javaexpress.reviewms.review.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<Review> getAllReviews(Long companyId) {

		return reviewRepository.findByCompanyId(companyId);
	}

	@Override
	public boolean addReview(Long companyId, Review review) {
		if(companyId!=null && review!= null) {
		
			review.setCompanyId(companyId);
			reviewRepository.save(review);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Review getReview(Long reviewId) {
		return reviewRepository.findById(reviewId).orElse(null);

	}

	@Override
	public boolean updateReview (Long reviewId, Review updatedReview) {
		Review review = reviewRepository.findById(reviewId).orElse(null);
		if (reviewId != null) {
			review.setTitle(updatedReview.getTitle());
			review.setDescription(updatedReview.getDescription());
			review.setRating(updatedReview.getRating());
			review.setCompanyId(updatedReview.getCompanyId());			
			reviewRepository.save(updatedReview);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteReview(Long reviewId) {
		
		if (reviewRepository.existsById(reviewId)) {
			reviewRepository.deleteById(reviewId);
			return true;
		}
		return false;
	}

}
