package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;

import org.haegerp.controller.ClientOfferController;
import org.haegerp.entity.ClientBill;
import org.haegerp.entity.ClientOffer;
import org.haegerp.entity.repository.client.ClientBillRepository;
import org.haegerp.entity.repository.client.ClientOfferRepository;
import org.haegerp.entity.repository.client.ClientRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.exception.LengthOverflowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientOfferControllerImpl implements ClientOfferController {

	@Autowired
	private ClientOfferRepository clientOfferRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ClientBillRepository clientBillRepository;
	
	private String search;
	private int enableAll = 1;
	private int enableSearch = 0;
	
	private int pageNumber;
	private Page<ClientOffer> page;
	
        @Override
	public Page<ClientOffer> getPage() {
		return page;
	}
	
	public ClientOfferControllerImpl() {
		pageNumber = 0;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
        @Override
	public Object[][] loadTableRows(int size) {
		Page<ClientOffer> coPage = this.loadPage(size);
        Object[][] rows = new Object[coPage.getContent().size()][9];
        for (int i = 0; i < coPage.getContent().size(); i++) {
        	ClientOffer clientOffer = coPage.getContent().get(i);
        	
        	rows[i][0] = clientOffer.getClient().getName();
        	rows[i][1] = clientOffer.getEmployee().getName();
			rows[i][2] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOffer.getOfferDate());
			rows[i][3] = (clientOffer.getSendDate() == null) ? "Order not sent" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOffer.getSendDate());
			rows[i][4] = clientOffer.getTotal();
			if (clientOffer.getClientBill() == null)
			{
				rows[i][5] = "Bill not processed";
				rows[i][6] = "Bill not paid";
			} else {
				rows[i][5] = (clientOffer.getClientBill().getBilledDate() == null) ? "Bill not processed" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOffer.getClientBill().getBilledDate());
				rows[i][6] = (clientOffer.getClientBill().getPaidDate() == null) ? "Bill not paid" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOffer.getClientBill().getPaidDate());
			}
			rows[i][7] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOffer.getLastModifiedDate());
			rows[i][8] = employeeRepository.findOne(clientOffer.getIdEmployeeModify()).getName();
		}
        
        return rows;
	}

        @Override
	public void setSearch(String value, int size) {
		enableSearch = 0;
		if (value.equals("")){
			enableAll = 1;
		} else {
			enableSearch = 1;
			enableAll = 0;
			search = value;
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
	public Page<ClientOffer> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = clientOfferRepository.findWithFilters(enableSearch, search,
                                                                    enableAll, pageRequest);
		return page;
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ClientOffer save(ClientOffer clientOffer) {
		ClientOffer savedClientOffer = clientOfferRepository.save(clientOffer);
		return savedClientOffer;
	}
	
        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ClientOffer updateClientOffer(ClientOffer clientOffer) throws LengthOverflowException{
		ClientOffer newClientOffer = clientOfferRepository.findOne(clientOffer.getIdClientOffer());
		
		newClientOffer.setDescription(clientOffer.getDescription());
		newClientOffer.setEmployee(clientOffer.getEmployee());
		newClientOffer.setIdEmployeeModify(clientOffer.getIdEmployeeModify());
		newClientOffer.setLastModifiedDate(clientOffer.getLastModifiedDate());
		newClientOffer.setOfferDate(clientOffer.getOfferDate());
		newClientOffer.setSendDate(clientOffer.getSendDate());
		newClientOffer.setClient(clientOffer.getClient());
		newClientOffer.setClientBill(clientOffer.getClientBill());
		newClientOffer.setClientOfferDetail(clientOffer.getClientOfferDetail());
		newClientOffer.calculateTotal();
		//TODO: Outstanding
		
		newClientOffer = clientOfferRepository.save(newClientOffer);
		
		return newClientOffer;
	}

        @Override
	public void delete(ClientOffer clientOffer) {
		clientOfferRepository.delete(clientOffer);
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ClientOffer newClientOffer(ClientOffer clientOffer) throws LengthOverflowException {
		ClientOffer newClientOffer = new ClientOffer();
		
		newClientOffer.setDescription(clientOffer.getDescription());
		newClientOffer.setEmployee(clientOffer.getEmployee());
		newClientOffer.setIdEmployeeModify(clientOffer.getIdEmployeeModify());
		newClientOffer.setLastModifiedDate(clientOffer.getLastModifiedDate());
		newClientOffer.setOfferDate(clientOffer.getOfferDate());
		newClientOffer.setSendDate(clientOffer.getSendDate());
		newClientOffer.setClient(clientOffer.getClient());
		newClientOffer.setClientBill(clientOffer.getClientBill());
		newClientOffer.setClientOfferDetail(clientOffer.getClientOfferDetail());
		//TODO: Outstanding
		
		newClientOffer = clientOfferRepository.save(newClientOffer);
		
		return newClientOffer;
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ClientBill getClientBillById(long idClientBill) {
		return clientBillRepository.findOne(idClientBill);
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ClientBill saveBill(ClientBill clientBill) {
		return clientBillRepository.save(clientBill);
	}
	
}
