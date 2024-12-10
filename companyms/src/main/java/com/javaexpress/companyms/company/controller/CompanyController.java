package com.javaexpress.companyms.company.controller;

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

import com.javaexpress.companyms.company.bean.Company;
import com.javaexpress.companyms.company.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies(){
		return new ResponseEntity<>(companyService.findAll(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Company> createCompany(@RequestBody Company company) {
		return new ResponseEntity<>(companyService.createCompany(company),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getComapnyById(@PathVariable Long id) {
		Company company = companyService.getCompanyById(id);
		if (company == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(company, HttpStatus.OK);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {

		boolean deleted = companyService.deleteCompanyById(id);
		if (deleted) {
			return new ResponseEntity<>("Company Deleted Successfully.", HttpStatus.OK);			
		} else {
			return new ResponseEntity<>("Company Not Found.",HttpStatus.NOT_FOUND);			
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
		
		if(companyService.updateCompanyById(id, updatedCompany)) {
			return new ResponseEntity<>("Company Updated Successfully.", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		

	}
	

}
