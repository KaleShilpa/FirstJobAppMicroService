package com.javaexpress.companyms.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.companyms.company.bean.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
