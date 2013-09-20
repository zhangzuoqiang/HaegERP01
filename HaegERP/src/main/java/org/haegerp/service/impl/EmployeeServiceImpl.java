package org.haegerp.service.impl;

import java.text.SimpleDateFormat;

import org.haegerp.service.EmployeeService;
import org.haegerp.entity.Employee;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private int pageNumber;
    private Page<Employee> page;
    private Long idSalaryCategory = 0L;
    private int disableSalaryCategory = 1;
    private Long idUserGroup = 0L;
    private int disableUserGroup = 1;
    private Long idDivision = 0L;
    private int disableDivision = 1;
    private String search = "";
    private int enableSearch = 0;
    private int disableSearchFilters = 1;
    private int disableSearchCategory = 1;
    private int enableAll = 1;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Employee isLoginCorrect(String username, String passwordMD5) {
        Employee employee = employeeRepository.login(username, passwordMD5);
        if (employee != null) {
            employee.getUserGroup().getPermissions().size();
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public Page<Employee> getPage() {
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
    public void setSalaryCategory(Long idSalaryCategory, int size) {
        if (idSalaryCategory == -1) {
            disableSalaryCategory = 1;
            if (enableSearch + disableUserGroup + disableDivision == 2) {
                enableAll = 1;
            }
            if (disableUserGroup + disableDivision == 2) {
                disableSearchCategory = 1;
            }
        } else {
            this.idSalaryCategory = idSalaryCategory;
            disableSalaryCategory = 0;
            enableAll = 0;
            disableSearchCategory = 0;
            pageNumber = 0;
        }
    }

    @Override
    public void setUserGroup(Long idUserGroup, int size) {
        if (idUserGroup == -1) {
            disableUserGroup = 1;
            if (enableSearch + disableSalaryCategory + disableDivision == 2) {
                enableAll = 1;
            }
            if (disableDivision + disableSalaryCategory == 2) {
                disableSearchCategory = 1;
            }
        } else {
            this.idUserGroup = idUserGroup;
            disableUserGroup = 0;
            enableAll = 0;
            disableSearchCategory = 0;
            pageNumber = 0;
        }
    }

    @Override
    public void setDivision(Long idDivision, int size) {
        if (idDivision == -1) {
            disableDivision = 1;
            if (enableSearch + disableUserGroup + disableSalaryCategory == 2) {
                enableAll = 1;
            }
            if (disableSalaryCategory + disableUserGroup == 2) {
                disableSearchCategory = 1;
            }
        } else {
            this.idDivision = idDivision;
            disableDivision = 0;
            enableAll = 0;
            disableSearchCategory = 0;
            pageNumber = 0;
        }
    }

    @Override
    public void setSearch(String value, int size) {
        if (value.equals("")) {
            enableSearch = 0;
            disableSearchFilters = 1;
            if (disableSalaryCategory + disableDivision + disableUserGroup == 3) {
                enableAll = 1;
            }
        } else {
            search = value;
            enableSearch = 1;
            disableSearchFilters = 0;
            enableAll = 0;
            pageNumber = 0;
        }
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Employee save(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<Employee> loadPage(int size) {
        PageRequest pageRequest = new PageRequest(pageNumber, size);
        this.page = employeeRepository.findWithFilters(disableSearchCategory,
                disableDivision, idDivision,
                disableSalaryCategory, idSalaryCategory,
                disableUserGroup, idUserGroup,
                disableSearchFilters,
                enableSearch, search,
                enableAll, pageRequest);
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object[][] loadTableRows(int size) {
        Page<Employee> ePage = this.loadPage(size);
        Object[][] rows = new Object[ePage.getContent().size()][8];
        for (int i = 0; i < ePage.getContent().size(); i++) {
            Employee employee = ePage.getContent().get(i);

            rows[i][0] = employee.getIdEmployee();
            rows[i][1] = employee.getIdCard();
            rows[i][2] = employee.getName();
            rows[i][3] = employee.getDivision().getName();
            rows[i][4] = employee.getUserGroup().getName();
            rows[i][5] = employee.getSalaryCategory().getSalaryFrom() + " - " + employee.getSalaryCategory().getSalaryTo();
            rows[i][6] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(employee.getLastModifiedDate());
            rows[i][7] = employeeRepository.findOne(employee.getIdEmployeeModify()).getName();
        }

        return rows;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String getEmployeeName(long id) {
        Employee employee = employeeRepository.findOne(id);
        return employee.getName();
    }

    @Override
    public Employee getEmployeeById(long idEmployee) {
        return employeeRepository.findOne(idEmployee);
    }

    @Override
    public boolean isEmployeeOffersEmpty(long idEmployee) {
        Employee employee = employeeRepository.findOne(idEmployee);
        return employee.getClientOffers().isEmpty();
    }

    @Override
    public boolean isEmployeeOrdersEmpty(long idEmployee) {
        Employee employee = employeeRepository.findOne(idEmployee);
        return employee.getSupplierOrders().isEmpty();
    }
}
