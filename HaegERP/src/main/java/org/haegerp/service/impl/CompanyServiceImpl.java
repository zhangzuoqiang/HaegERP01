package org.haegerp.service.impl;

import org.haegerp.service.CompanyService;
import org.haegerp.entity.Company;
import org.haegerp.entity.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
        @Override
	public Company getCompany() {
		return companyRepository.findOne(1L);
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Company save(Company company) {
		Company savedCompany = companyRepository.save(company);
		return savedCompany;
	}

}
