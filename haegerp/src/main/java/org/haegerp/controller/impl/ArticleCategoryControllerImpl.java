package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.haegerp.controller.ArticleCategoryController;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleCategoryControllerImpl implements ArticleCategoryController {

	@Autowired
	private ArticleCategoryRepository articleCategoryRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private int pageNumber;
	
	private Page<ArticleCategory> page;
	
	private String textToFilter;
	
	private int enableAll;

	public ArticleCategoryControllerImpl() {
		pageNumber = 0;
	}

	public List<ArticleCategory> getAllCategories() {
		return articleCategoryRepository.findAll();
	}

	public Page<ArticleCategory> getPage() {
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

	public void delete(ArticleCategory articleCategory) {
		articleCategoryRepository.delete(articleCategory);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ArticleCategory save(ArticleCategory articleCategory) {
		ArticleCategory savedCategory = articleCategoryRepository.save(articleCategory);
		return savedCategory;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Page<ArticleCategory> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = articleCategoryRepository.findWithFilters(textToFilter, enableAll, pageRequest);
		return page;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int size) {
		Page<ArticleCategory> page = this.loadPage(size);
        Object[][] rows = new Object[page.getContent().size()][5];
        for (int i = 0; i < page.getContent().size(); i++) {
        	ArticleCategory articleCategory = page.getContent().get(i);
        	
        	rows[i][0] = articleCategory.getName();
        	rows[i][1] = articleCategory.getArticles().size();
        	rows[i][2] = articleCategory.getDescription();
        	rows[i][3] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(articleCategory.getLastModifiedDate());
        	rows[i][4] = employeeRepository.findOne(articleCategory.getIdEmployeeModify()).getName();
		}
        
        return rows;
	}
}
