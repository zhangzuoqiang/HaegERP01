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
	
	String search;
	int enableAll = 1;
	int enableSearch = 0;
	
	private int pageNumber;
	private Page<Supplier> page;
	
        @Override
	public Page<Supplier> getPage() {
		return page;
	}
	
	public SupplierControllerImpl() {
		pageNumber = 0;
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Object[][] loadTableRows(int size) {
		Page<Supplier> sPage = this.loadPage(size);
        Object[][] rows = new Object[sPage.getContent().size()][8];
        for (int i = 0; i < sPage.getContent().size(); i++) {
        	Supplier supplier = sPage.getContent().get(i);
        	
                rows[i][0] = supplier.getIdBusinessPartner();
        	rows[i][1] = supplier.getTaxId();
        	rows[i][2] = supplier.getName();
                rows[i][3] = supplier.getEmail();
                rows[i][4] = supplier.getCity();
                rows[i][5] = supplier.getCountry();
                rows[i][6] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplier.getLastModifiedDate());
                rows[i][7] = employeeRepository.findOne(supplier.getIdEmployeeModify()).getName();
		}
        
        return rows;
	}

        @Override
	public void setSearch(String value, int size) {
		enableSearch = 0;
		if (value.equals("")){
                    enableAll = 1;
		} else {
                    search = value;
                    enableAll = 0;
                    pageNumber = 0;
		}
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
		if (page.hasNextPage()){
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
	@Transactional(propagation = Propagation.REQUIRED)
	public Page<Supplier> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = supplierRepository.findWithFilters(enableSearch, search,
                                                                enableAll, pageRequest);
		return page;
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Supplier save(Supplier supplier) {
		Supplier savedSupplier = supplierRepository.save(supplier);
		return savedSupplier;
	}

        @Override
	public void delete(Supplier supplier) {
		supplierRepository.delete(supplier);
	}

        @Override
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

        @Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Supplier getSupplierById(long idSupplier) {
		return supplierRepository.findOne(idSupplier);
	}

        @Override
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
