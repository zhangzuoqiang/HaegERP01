package org.haegerp.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.haegerp.service.UserGroupService;
import org.haegerp.entity.UserGroup;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.entity.repository.employee.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupRepository userGroupRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private int pageNumber;
	
	private Page<UserGroup> page;
	
	private String textToFilter;
	
	private int enableAll;

	public UserGroupServiceImpl() {
		pageNumber = 0;
	}

        @Override
	public List<UserGroup> getAllGroups() {
		return userGroupRepository.findAll();
	}

        @Override
	public Page<UserGroup> getPage() {
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
	public void delete(UserGroup userGroup) {
		userGroupRepository.delete(userGroup);
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserGroup save(UserGroup userGroup) {
		UserGroup savedUserGroup = userGroupRepository.save(userGroup);
		return savedUserGroup;
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Page<UserGroup> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = userGroupRepository.findWithFilters(textToFilter, enableAll, pageRequest);
		return page;
	}

        @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int size) {
		Page<UserGroup> ugPage = this.loadPage(size);
        Object[][] rows = new Object[ugPage.getContent().size()][6];
        for (int i = 0; i < ugPage.getContent().size(); i++) {
            UserGroup userGroup = ugPage.getContent().get(i);

            rows[i][0] = userGroup.getIdUserGroup();
            rows[i][1] = userGroup.getName();
            rows[i][2] = userGroup.getEmployees().size();
            rows[i][3] = userGroup.getDescription();
            rows[i][4] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(userGroup.getLastModifiedDate());
            rows[i][5] = employeeRepository.findOne(userGroup.getIdEmployeeModify()).getName();
        }
        
        return rows;
	}

        @Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserGroup getUserGroupById(long id) {
		return userGroupRepository.findOne(id);
	}

    @Override
    public boolean isUserGroupEmpty(long idUserGroup) {
        UserGroup userGroup = userGroupRepository.findOne(idUserGroup);
        return userGroup.getEmployees().isEmpty();
    }
}
