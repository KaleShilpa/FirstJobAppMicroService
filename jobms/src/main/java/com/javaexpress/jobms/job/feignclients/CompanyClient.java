package com.javaexpress.jobms.job.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.jobms.job.external.Company;

@FeignClient(name="company-service", url="${company-service.url}")
public interface CompanyClient {

	@GetMapping("/companies/{id}")
	Company getComapnyById(@PathVariable Long id);
}
