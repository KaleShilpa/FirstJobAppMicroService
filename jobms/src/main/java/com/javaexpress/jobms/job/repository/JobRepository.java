package com.javaexpress.jobms.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.jobms.job.bean.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
