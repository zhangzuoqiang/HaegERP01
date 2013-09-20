package org.haegerp.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.haegerp.service.DivisionService;
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
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    private int pageNumber;
    private Page<Division> page;
    private String textToFilter;
    private int enableAll;

    public DivisionServiceImpl() {
        pageNumber = 0;
    }

    @Override
    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    @Override
    public Page<Division> getPage() {
        return page;
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
        if (page.hasPreviousPage()) {
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

    @Override
    public void setSearch(String value, int size) {
        if (value.length() > 0) {
            enableAll = 0;
            textToFilter = value;
        } else {
            enableAll = 1;
            textToFilter = "";
        }
    }

    @Override
    public void delete(Division division) {
        divisionRepository.delete(division);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Division save(Division division) {
        Division savedDivision = divisionRepository.save(division);
        return savedDivision;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<Division> loadPage(int size) {
        PageRequest pageRequest = new PageRequest(pageNumber, size);
        this.page = divisionRepository.findWithFilters(textToFilter, enableAll, pageRequest);
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object[][] loadTableRows(int size) {
        Page<Division> dPage = this.loadPage(size);
        Object[][] rows = new Object[dPage.getContent().size()][6];
        for (int i = 0; i < dPage.getContent().size(); i++) {
            Division division = dPage.getContent().get(i);

            rows[i][0] = division.getIdDivision();
            rows[i][1] = division.getName();
            rows[i][2] = division.getEmployees().size();
            rows[i][3] = division.getDescription();
            rows[i][4] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(division.getLastModifiedDate());
            rows[i][5] = employeeRepository.findOne(division.getIdEmployeeModify()).getName();
        }

        return rows;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Division getDivisionById(long id) {
        return divisionRepository.findOne(id);
    }

    @Override
    public boolean isDivisionEmpty(long idDivision) {
        Division division = divisionRepository.findOne(idDivision);
        return division.getEmployees().isEmpty();
    }
}
