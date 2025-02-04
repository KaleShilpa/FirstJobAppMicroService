package com.javaexpress.jobms.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.jobms.job.bean.Job;
import com.javaexpress.jobms.job.dto.JobDTO;
import com.javaexpress.jobms.job.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private JobService jobService;

	@GetMapping
	public ResponseEntity<List<JobDTO>> findAll() {
		List<JobDTO> jobList = jobService.findAll();
		return new ResponseEntity<>(jobList, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Job> createJob(@RequestBody Job job) {
		return new ResponseEntity<>(jobService.createJob(job), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
		JobDTO jobWithCompanyDTO = jobService.getJobById(id);
		if (jobWithCompanyDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(jobWithCompanyDTO, HttpStatus.OK);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long id) {

		if (jobService.deleteJobById(id)) {
			return new ResponseEntity<>("Job Deleted Successfully.", HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {

		if (jobService.updateJobById(id, updatedJob)) {
			return new ResponseEntity<>("Job Updated Successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
