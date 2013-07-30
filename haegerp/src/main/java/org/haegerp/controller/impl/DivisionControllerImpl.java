package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.haegerp.controller.DivisionController;
import org.haegerp.entity.Division;
import org.haegerp.entity.repository.employee.DivisionRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DivisionControllerImpl implements DivisionController {

	@Autowired
	private DivisionRepository divisionRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private int pageNumber;
	
	private Page<Division> page;
	
	private String textToFilter;
	
	private int enableAll;

	public DivisionControllerImpl() {
		pageNumber = 0;
	}

	public List<Division> getAllDivisions() {
		return divisionRepository.findAll();
	}

	public Page<Division> getPage() {
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

	public void delete(Division division) {
		divisionRepository.delete(division);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Division save(Division division) {
		Division savedDivision = divisionRepository.save(division);
		return savedDivision;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Page<Division> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = divisionRepository.findWithFilters(textToFilter, enableAll, pageRequest);
		return page;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int size) {
		Page<Division> page = this.loadPage(size);
        Object[][] rows = new Object[page.getContent().size()][5];
        for (int i = 0; i < page.getContent().size(); i++) {
        	Division division = page.getContent().get(i);
        	
        	rows[i][0] = division.getName();
        	rows[i][1] = division.getEmployees().size();
        	rows[i][2] = division.getDescription();
        	rows[i][3] = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(division.getLastModifiedDate());
        	rows[i][4] = employeeRepository.findOne(division.getIdEmployeeModify()).getName();
		}
        
        return rows;
	}
}
