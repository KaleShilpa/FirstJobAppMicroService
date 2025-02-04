package com.javaexpress.jobms.job.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javaexpress.jobms.job.bean.Job;
import com.javaexpress.jobms.job.dto.JobDTO;
import com.javaexpress.jobms.job.external.Company;
import com.javaexpress.jobms.job.external.Review;
import com.javaexpress.jobms.job.feignclients.CompanyClient;
import com.javaexpress.jobms.job.feignclients.ReviewClient;
import com.javaexpress.jobms.job.repository.JobRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CompanyClient companyClient;
	
	@Autowired
	private ReviewClient reviewClient;

	@Override
	@CircuitBreaker(name="companyBreaker",fallbackMethod="companyBreakerFallback")
	public List<JobDTO> findAll() {

		List<Job> jobs = jobRepository.findAll();
		List<JobDTO> jobWithCompanyDTOs = jobs.stream().map(this::convertToDTO).collect(Collectors.toList());

		return jobWithCompanyDTOs;
	}
	
	public List<String> companyBreakerFallback(Exception e) {
		List<String> list = new ArrayList<>();
		list.add("Dummy Data.");
		return list;
	}

	private JobDTO convertToDTO(Job job) {
		JobDTO jobDTO = new JobDTO();
		jobDTO.setDescription(job.getDescription());
		jobDTO.setId(job.getId());
		jobDTO.setLocation(job.getLocation());
		jobDTO.setMaxSalary(job.getMaxSalary());
		jobDTO.setMinSalary(job.getMinSalary());
		jobDTO.setTitle(job.getTitle());
		
		Company company = companyClient.getComapnyById(job.getCompanyId());
		List<Review> reviews = reviewClient.getAllReviews(job.getCompanyId());
		
		//Company company = restTemplate.getForObject("http://companyms:8081/companies/" + job.getCompanyId(),Company.class);

		//ParameterizedTypeReference<List<Review>> myBean = new ParameterizedTypeReference<List<Review>>() {};

//		ResponseEntity<List<Review>> response = restTemplate.exchange("http://reviewms:8083/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET, null, myBean);
		
		jobDTO.setReview(reviews);
		jobDTO.setCompany(company);

		return jobDTO;
	}

	@Override
	public Job createJob(Job job) {

		return jobRepository.save(job);
	}

	@Override
	public JobDTO getJobById(Long id) {
		Job job = jobRepository.findById(id).orElse(null);

		return convertToDTO(job);
	}

	@Override
	public boolean deleteJobById(Long id) {
		boolean isDeleted = false;
		if (jobRepository.existsById(id)) {
			jobRepository.deleteById(id);
			isDeleted = true;
		}
		return isDeleted;

	}

	@Override
	public boolean updateJobById(Long id, Job updatedJob) {
		Optional<Job> jobOptional = jobRepository.findById(id);

		if (jobOptional.isPresent()) {
			Job job = jobOptional.get();
			job.setDescription(updatedJob.getDescription());
			job.setLocation(updatedJob.getLocation());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setTitle(updatedJob.getTitle());
			jobRepository.save(job);
			return true;
		}

		return false;
	}

}
