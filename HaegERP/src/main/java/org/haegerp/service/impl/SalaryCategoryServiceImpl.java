package org.haegerp.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.haegerp.service.SalaryCategoryService;
import org.haegerp.entity.SalaryCategory;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.entity.repository.employee.SalaryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaryCategoryServiceImpl implements SalaryCategoryService {

	@Autowired
	private SalaryCategoryRepository salaryCategoryRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private int pageNumber;
	
	private Page<SalaryCategory> page;
	
	private String textToFilter;
	
	private int enableAll;

	public SalaryCategoryServiceImpl() {
		pageNumber = 0;
	}

        @Override
	public List<SalaryCategory> getAllCategories() {
		return salaryCategoryRepository.findAll();
	}

        @Override
	public Page<SalaryCategory> getPage() {
		return page;
	}

        @Override
	public boolean getNextPage(int size) {
		if (page.hasNextPage()){
			pageNumber++;
			page = loadPage(size);
			return true;
		}
		else
			return false;
	}

        @Override
	public boolean getPreviousPage(int size) {
		if (page.hasPreviousPage()){
			pageNumber--;
			page = loadPage(size);
			return true;
		}
		else
			return false;
	}

        @Override
	public boolean getFirstPage(int size) {
		pageNumber = 0;
		page = loadPage(size);
		return true;
	}

        @Override
	public void setSearch(String value, int size) {
		if (value.length() > 0){
			enableAll = 0;
			textToFilter = value;
		} else {
			enableAll = 1;
			textToFilter = "";
		}
	}

        @Override
	public void delete(SalaryCategory salaryCategory) {
		salaryCategoryRepository.delete(salaryCategory);
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SalaryCategory save(SalaryCategory salaryCategory) {
		SalaryCategory savedCategory = salaryCategoryRepository.save(salaryCategory);
		return savedCategory;
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Page<SalaryCategory> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = salaryCategoryRepository.findWithFilters(textToFilter, enableAll, pageRequest);
		return page;
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int size) {
		Page<SalaryCategory> scPage = this.loadPage(size);
        Object[][] rows = new Object[scPage.getContent().size()][7];
        for (int i = 0; i < scPage.getContent().size(); i++) {
        	SalaryCategory salaryCategory = scPage.getContent().get(i);
        	
                rows[i][0] = salaryCategory.getIdSalaryCategory();
        	rows[i][1] = salaryCategory.getSalaryFrom();
        	rows[i][2] = salaryCategory.getSalaryTo();
        	rows[i][3] = salaryCategory.getEmployees().size();
        	rows[i][4] = salaryCategory.getDescription();
        	rows[i][5] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(salaryCategory.getLastModifiedDate());
        	rows[i][6] = employeeRepository.findOne(salaryCategory.getIdEmployeeModify()).getName();
		}
        
        return rows;
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SalaryCategory getSalaryCategoryById(long id) {
		return salaryCategoryRepository.findOne(id);
	}

    @Override
    public boolean isSalaryCategoryEmpty(long idCategory) {
        SalaryCategory salaryCategory = salaryCategoryRepository.findOne(idCategory);
        return salaryCategory.getEmployees().isEmpty();
    }
}
