package org.haegerp.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import org.haegerp.service.ClientService;
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
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    String search;
    private long idClientCategory;
    int enableAll = 1;
    int disableSearchCategory = 1,
            disableSearchFilters = 1,
            enableCategory = 0,
            enableSearch = 0;
    private int pageNumber;
    private Page<Client> page;

    @Override
    public Page<Client> getPage() {
        return page;
    }

    public ClientServiceImpl() {
        pageNumber = 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Object[][] loadTableRows(int size) {
        Page<Client> cPage = this.loadPage(size);
        Object[][] rows = new Object[cPage.getContent().size()][9];
        for (int i = 0; i < cPage.getContent().size(); i++) {
            Client client = cPage.getContent().get(i);

            rows[i][0] = client.getIdBusinessPartner();
            rows[i][1] = client.getTaxId();
            rows[i][2] = client.getName();
            rows[i][3] = client.getClientCategory().getName();
            rows[i][4] = client.getEmail();
            rows[i][5] = client.getCity();
            rows[i][6] = client.getCountry();
            rows[i][7] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(client.getLastModifiedDate());
            rows[i][8] = employeeRepository.findOne(client.getIdEmployeeModify()).getName();
        }

        return rows;
    }

    @Override
    public void setSearch(String value, int size) {
        enableSearch = 0;
        if (value.equals("")) {
            disableSearchFilters = 1;
            if (enableCategory == 0) {
                enableAll = 1;
            }
        } else {
            try {
                enableSearch = 1;
                search = value;
                disableSearchFilters = 0;
                enableAll = 0;
                pageNumber = 0;
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    @Override
    public void setCategory(long idClientCategory, int size) {
        if (idClientCategory == -1) {
            enableCategory = 0;
            if (enableSearch == 0) {
                enableAll = 1;
            }
            disableSearchCategory = 1;
        } else {
            this.idClientCategory = idClientCategory;
            enableCategory = 1;
            enableAll = 0;
            disableSearchCategory = 0;
            pageNumber = 0;
        }
    }

    @Override
    public boolean getNextPage(int size) {
        if (page.hasNextPage()) {
            pageNumber++;
            page = loadPage(size);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getPreviousPage(int size) {
        if (page.hasNextPage()) {
            pageNumber--;
            page = loadPage(size);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getFirstPage(int size) {
        pageNumber = 0;
        page = loadPage(size);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Page<Client> loadPage(int size) {
        PageRequest pageRequest = new PageRequest(pageNumber, size);
        this.page = clientRepository.findWithFilters(enableCategory, idClientCategory,
                disableSearchCategory, disableSearchFilters,
                enableSearch, search,
                enableAll, pageRequest);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Client save(Client client) {
        Client savedClient = clientRepository.save(client);
        return savedClient;
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Transactional
    @Override
    public void deleteAllArticleFromCategory(ClientCategory clientCategory) {
        clientRepository.deleteInBatch(clientCategory.getClients());
        clientCategory.setClients(new HashSet<Client>(0));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Client getClientById(long idClient) {
        return clientRepository.findOne(idClient);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object[][] loadAllTableRows(int enableSearch, String search, int enableAll) {
        List<Client> list = clientRepository.findAllClients(enableSearch, search, enableAll);
        Object[][] rows = new Object[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            Client client = list.get(i);

            rows[i][0] = client.getIdBusinessPartner();
            rows[i][1] = client.getTaxId();
            rows[i][2] = client.getName();
            rows[i][3] = client.getEmail();
            rows[i][4] = client.getCity();
        }
        return rows;
    }

    @Override
    public boolean isClientOffersEmpty(long idClient) {
        Client client = clientRepository.findOne(idClient);
        return client.getOffers().isEmpty();
    }
}
