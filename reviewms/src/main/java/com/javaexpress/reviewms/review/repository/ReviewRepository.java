package com.javaexpress.reviewms.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.reviewms.review.bean.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByCompanyId(Long companyId);

}
