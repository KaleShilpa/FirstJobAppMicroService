package com.javaexpress.companyms.company.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.companyms.company.bean.Company;
import com.javaexpress.companyms.company.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public List<Company> findAll() {

		return companyRepository.findAll();
	}

	@Override
	public Company createCompany(Company company) {

		return companyRepository.save(company);
	}

	@Override
	public Company getCompanyById(Long id) {

		return companyRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteCompanyById(Long id) {

		if (companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean updateCompanyById(Long id, Company updatedCompany) {

		Optional<Company> companyOptional = companyRepository.findById(id);

		if (companyOptional.isPresent()) {
			Company company = companyOptional.get();
			company.setDescription(updatedCompany.getDescription());
			company.setName(updatedCompany.getName());
			companyRepository.save(company);
			return true;
		}

		return false;

	}

}
