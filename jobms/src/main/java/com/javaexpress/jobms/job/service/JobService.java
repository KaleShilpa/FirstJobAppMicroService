package com.javaexpress.jobms.job.service;

import java.util.List;

import com.javaexpress.jobms.job.bean.Job;
import com.javaexpress.jobms.job.dto.JobDTO;

public interface JobService {
	
	public List<JobDTO> findAll();
	
	public Job createJob(Job job);
	
	public JobDTO getJobById(Long id);
	
	public boolean deleteJobById(Long id);
	
	public boolean updateJobById(Long id, Job job);
	
}
