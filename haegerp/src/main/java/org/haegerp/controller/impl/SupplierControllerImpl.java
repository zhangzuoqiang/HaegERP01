package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.haegerp.controller.SupplierController;
import org.haegerp.entity.Supplier;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.entity.repository.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierControllerImpl implements SupplierController {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	String taxId, name, email, city, country;
	int enableAll = 1;
	int enableTaxID = 0,
			enableName = 0,
			enableEmail = 0,
			enableCity = 0,
			enableCountry = 0;
	
	private int pageNumber;
	private Page<Supplier> page;
	
	public Page<Supplier> getPage() {
		return page;
	}
	
	public SupplierControllerImpl() {
		pageNumber = 0;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Object[][] loadTableRows(int size) {
		Page<Supplier> page = this.loadPage(size);
        Object[][] rows = new Object[page.getContent().size()][7];
        for (int i = 0; i < page.getContent().size(); i++) {
        	Supplier supplier = page.getContent().get(i);
        	
        	rows[i][0] = supplier.getTaxId();
        	rows[i][1] = supplier.getName();
			rows[i][2] = supplier.getEmail();
			rows[i][3] = supplier.getCity();
			rows[i][4] = supplier.getCountry();
			rows[i][5] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplier.getLastModifiedDate());
			rows[i][6] = employeeRepository.findOne(supplier.getIdEmployeeModify()).getName();
		}
        
        return rows;
	}

	public void setSearch(String value, int field, int size) {
		enableTaxID = enableName = enableEmail = enableCity = enableCountry = 0;
		if (value.equals("")){
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
				enableAll = 0;
				pageNumber = 0;
			} catch(Exception ex) {
				ex.printStackTrace();
			}
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
	public Page<Supplier> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = supplierRepository.findWithFilters(enableTaxID, taxId,
													enableName, name,
													enableEmail, email,
													enableCity, city,
													enableCountry, country,
													enableAll, pageRequest);
		return page;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Supplier save(Supplier supplier) {
		Supplier savedSupplier = supplierRepository.save(supplier);
		return savedSupplier;
	}

	public void delete(Supplier supplier) {
		supplierRepository.delete(supplier);
	}

	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Supplier getSupplierById(long idSupplier) {
		return supplierRepository.findOne(idSupplier);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadAllTableRows() {
		List<Supplier> list = supplierRepository.findAll();
        Object[][] rows = new Object[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
        	Supplier supplier = list.get(i);
        	
        	rows[i][0] = supplier.getIdBusinessPartner();
        	rows[i][1] = supplier.getTaxId();
        	rows[i][2] = supplier.getName();
			rows[i][3] = supplier.getEmail();
			rows[i][4] = supplier.getCity();
		}
        return rows;
	}

}
