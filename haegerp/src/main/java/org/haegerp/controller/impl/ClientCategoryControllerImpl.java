package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.haegerp.controller.ClientCategoryController;
import org.haegerp.entity.ClientCategory;
import org.haegerp.entity.repository.client.ClientCategoryRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientCategoryControllerImpl implements ClientCategoryController {

	@Autowired
	private ClientCategoryRepository clientCategoryRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private int pageNumber;
	
	private Page<ClientCategory> page;
	
	private String textToFilter;
	
	private int enableAll;
	
	public ClientCategoryControllerImpl() {
		pageNumber = 0;
	}
	
	public List<ClientCategory> getAllCategories() {
		return clientCategoryRepository.findAll();
	}

	public Page<ClientCategory> getPage() {
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

	public void delete(ClientCategory clientCategory) {
		clientCategoryRepository.delete(clientCategory);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ClientCategory save(ClientCategory clientCategory) {
		ClientCategory savedCategory = clientCategoryRepository.save(clientCategory);
		return savedCategory;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Page<ClientCategory> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = clientCategoryRepository.findWithFilters(textToFilter, enableAll, pageRequest);
		return page;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int size) {
		Page<ClientCategory> page = this.loadPage(size);
        Object[][] rows = new Object[page.getContent().size()][5];
        for (int i = 0; i < page.getContent().size(); i++) {
        	ClientCategory clientCategory = page.getContent().get(i);
        	
        	rows[i][0] = clientCategory.getName();
        	rows[i][1] = clientCategory.getClients().size();
        	rows[i][2] = clientCategory.getDescription();
        	rows[i][3] = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(clientCategory.getLastModifiedDate());
        	rows[i][4] = employeeRepository.findOne(clientCategory.getIdEmployeeModify()).getName();
		}
        
        return rows;
	}

}