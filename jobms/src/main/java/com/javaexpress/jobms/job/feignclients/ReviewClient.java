package com.javaexpress.jobms.job.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaexpress.jobms.job.external.Review;

@FeignClient(name="review-service",url="${review-service.url}")
public interface ReviewClient {

	@GetMapping("/reviews")
	List<Review> getAllReviews(@RequestParam Long companyId) ;
}
