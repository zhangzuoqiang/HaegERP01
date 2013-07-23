package org.haegerp.controller;

import java.util.List;

import org.haegerp.entity.ArticleCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface ArticleCategoryController {
	
	/**
	 * TODO: Commentary
	 * @return
	 */
	public List<ArticleCategory> getAllCategories();
	
	/**
	 * TODO: Commentary
	 * @return
	 */
	public Page<ArticleCategory> getPage();
	
	/**
	 * TODO: Commentary
	 * @param size
	 * @return
	 */
	public boolean getNextPage(int size);
	
	/**
	 * TODO: Commentary
	 * @param size
	 * @return
	 */
	public boolean getPreviousPage(int size);
	
	/**
	 * TODO: Commentary
	 * @param size
	 * @return
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * TODO: Commentary
	 * @param value
	 * @param field
	 * @param size
	 * @return
	 */
	public void setSearch(String value, int size);
	
	/**
	 * TODO: Commentary
	 * @param article
	 */
	public void delete(ArticleCategory articleCategory);
	
	/**
	 * TODO: Commentary
	 * @param article
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ArticleCategory save(ArticleCategory articleCategory);
	
	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param page Nummer der Seite
	 * @param size Grösse der Seite
	 * @return Seite mit den Artikeln
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<ArticleCategory> loadPage(int size);
	
	/**
	 * TODO: Commentary
	 * @param size
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
}
