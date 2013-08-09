package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.haegerp.controller.ClientController;
import org.haegerp.entity.Client;
import org.haegerp.entity.ClientCategory;
import org.haegerp.entity.repository.client.ClientRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientControllerImpl implements ClientController {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	String taxId, name, email, city, country;
	long idClientCategory;
	int enableAll = 1;
	int disableSearchCategory = 1,
			disableSearchFilters = 1,
			enableCategory = 0,
			enableTaxID = 0,
			enableName = 0,
			enableEmail = 0,
			enableCity = 0,
			enableCountry = 0;
	
	private int pageNumber;
	private Page<Client> page;
	
	public Page<Client> getPage() {
		return page;
	}
	
	public ClientControllerImpl() {
		pageNumber = 0;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Object[][] loadTableRows(int size) {
		Page<Client> page = this.loadPage(size);
        Object[][] rows = new Object[page.getContent().size()][8];
        for (int i = 0; i < page.getContent().size(); i++) {
        	Client client = page.getContent().get(i);
        	
        	rows[i][0] = client.getTaxId();
        	rows[i][1] = client.getName();
        	rows[i][2] = client.getClientCategory().getName();
			rows[i][3] = client.getEmail();
			rows[i][4] = client.getCity();
			rows[i][5] = client.getCountry();
			rows[i][6] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(client.getLastModifiedDate());
			rows[i][7] = employeeRepository.findOne(client.getIdEmployeeModify()).getName();
		}
        
        return rows;
	}

	public void setSearch(String value, int field, int size) {
		enableTaxID = enableName = enableEmail = enableCity = enableCountry = 0;
		if (value.equals("")){
			disableSearchFilters = 1;
			if (enableCategory == 0)
				enableAll = 1;
		} else {
			try {
				switch (field) {
				case 0:
					enableTaxID = 1;
					taxId = value;
					break;
				
				case 1:
					enableName = 1;
					name = value;
					break;
				
				case 2:
					enableEmail = 1;
					email = value;
					break;
					
				case 3:
					enableCity = 1;
					city = value;
					break;
					
				case 4:
					enableCountry = 1;
					country = value;
					break;
				}
				
				disableSearchFilters = 0;
				enableAll = 0;
				pageNumber = 0;
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void setCategory(long idClientCategory, int size) {
		if (idClientCategory == -1){
			enableCategory = 0;
			if (enableTaxID + enableName + enableEmail + enableCity + enableCountry == 0)
				enableAll = 1;
			disableSearchCategory = 1;
		} else {
			this.idClientCategory = idClientCategory;
			enableCategory = 1;
			enableAll = 0;
			disableSearchCategory = 0;
			pageNumber = 0;
		}
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
		if (page.hasNextPage()){
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

	@Transactional(propagation = Propagation.REQUIRED)
	public Page<Client> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = clientRepository.findWithFilters(enableCategory, idClientCategory,
													disableSearchCategory, disableSearchFilters,
													enableTaxID, taxId,
													enableName, name,
													enableEmail, email,
													enableCity, city,
													enableCountry, country,
													enableAll, pageRequest);
		return page;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Client save(Client client) {
		Client savedClient = clientRepository.save(client);
		return savedClient;
	}

	public void delete(Client client) {
		clientRepository.delete(client);
	}
	
	@Transactional
	public void deleteAllArticleFromCategory(ClientCategory clientCategory) {
		clientRepository.deleteInBatch(clientCategory.getClients());
		clientCategory.setClients(new HashSet<Client>(0));
	}

}
