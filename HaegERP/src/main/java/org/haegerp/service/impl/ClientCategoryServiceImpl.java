package org.haegerp.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.haegerp.service.ClientCategoryService;
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
public class ClientCategoryServiceImpl implements ClientCategoryService {

	@Autowired
	private ClientCategoryRepository clientCategoryRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private int pageNumber;
	
	private Page<ClientCategory> page;
	
	private String textToFilter;
	
	private int enableAll;
	
	public ClientCategoryServiceImpl() {
		pageNumber = 0;
	}
	
        @Override
	public List<ClientCategory> getAllCategories() {
		return clientCategoryRepository.findAll();
	}

        @Override
	public Page<ClientCategory> getPage() {
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
	public void delete(ClientCategory clientCategory) {
		clientCategoryRepository.delete(clientCategory);
	}

	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public ClientCategory save(ClientCategory clientCategory) {
		ClientCategory savedCategory = clientCategoryRepository.save(clientCategory);
		return savedCategory;
	}

	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public Page<ClientCategory> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = clientCategoryRepository.findWithFilters(textToFilter, enableAll, pageRequest);
		return page;
	}

	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public Object[][] loadTableRows(int size) {
		Page<ClientCategory> ccPage = this.loadPage(size);
        Object[][] rows = new Object[ccPage.getContent().size()][6];
        for (int i = 0; i < ccPage.getContent().size(); i++) {
        	ClientCategory clientCategory = ccPage.getContent().get(i);
        	
                rows[i][0] = clientCategory.getIdClientCategory();
        	rows[i][1] = clientCategory.getName();
        	rows[i][2] = clientCategory.getClients().size();
        	rows[i][3] = clientCategory.getDescription();
        	rows[i][4] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientCategory.getLastModifiedDate());
        	rows[i][5] = employeeRepository.findOne(clientCategory.getIdEmployeeModify()).getName();
        }
        
        return rows;
	}

	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public ClientCategory getClientCategoryId(long id) {
		return clientCategoryRepository.findOne(id);
	}

    @Override
    public boolean isCategoryClientsEmpty(long idCategory) {
        ClientCategory clientCategory = clientCategoryRepository.findOne(idCategory);
        return clientCategory.getClients().isEmpty();
    }

}