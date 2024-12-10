package com.javaexpress.companyms.company.service;

import java.util.List;

import com.javaexpress.companyms.company.bean.Company;

public interface CompanyService {

	public List<Company> findAll();

	public Company createCompany(Company company);

	public Company getCompanyById(Long id);

	public boolean deleteCompanyById(Long id);

	public boolean updateCompanyById(Long id, Company company);

}
