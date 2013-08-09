package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.haegerp.controller.SalaryCategoryController;
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
public class SalaryCategoryControllerImpl implements SalaryCategoryController {

	@Autowired
	private SalaryCategoryRepository salaryCategoryRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private int pageNumber;
	
	private Page<SalaryCategory> page;
	
	private String textToFilter;
	
	private int enableAll;

	public SalaryCategoryControllerImpl() {
		pageNumber = 0;
	}

	public List<SalaryCategory> getAllCategories() {
		return salaryCategoryRepository.findAll();
	}

	public Page<SalaryCategory> getPage() {
		return page;
	}

	public boolean getNextPage(int size) {
		if (page.hasNextPage()){
			pageNumber++;
			page = loadPage(size);
			return true;
		}
		else
			return false;
	}

	public boolean getPreviousPage(int size) {
		if (page.hasPreviousPage()){
			pageNumber--;
			page = loadPage(size);
			return true;
		}
		else
			return false;
	}

	public boolean getFirstPage(int size) {
		pageNumber = 0;
		page = loadPage(size);
		return true;
	}

	public void setSearch(String value, int size) {
		if (value.length() > 0){
			enableAll = 0;
			textToFilter = value;
		} else {
			enableAll = 1;
			textToFilter = "";
		}
	}

	public void delete(SalaryCategory salaryCategory) {
		salaryCategoryRepository.delete(salaryCategory);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SalaryCategory save(SalaryCategory salaryCategory) {
		SalaryCategory savedCategory = salaryCategoryRepository.save(salaryCategory);
		return savedCategory;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Page<SalaryCategory> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = salaryCategoryRepository.findWithFilters(textToFilter, enableAll, pageRequest);
		return page;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int size) {
		Page<SalaryCategory> page = this.loadPage(size);
        Object[][] rows = new Object[page.getContent().size()][6];
        for (int i = 0; i < page.getContent().size(); i++) {
        	SalaryCategory salaryCategory = page.getContent().get(i);
        	
        	rows[i][0] = salaryCategory.getSalaryFrom();
        	rows[i][1] = salaryCategory.getSalaryTo();
        	rows[i][2] = salaryCategory.getEmployees().size();
        	rows[i][3] = salaryCategory.getDescription();
        	rows[i][4] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(salaryCategory.getLastModifiedDate());
        	rows[i][5] = employeeRepository.findOne(salaryCategory.getIdEmployeeModify()).getName();
		}
        
        return rows;
	}
}
