package org.haegerp.controller;

import java.util.List;

import org.haegerp.entity.ClientCategory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ClientCategoryController {

	/**
	 * TODO: Commentary
	 * @return
	 */
	public List<ClientCategory> getAllCategories();
	
	/**
	 * TODO: Commentary
	 * @return
	 */
	public Page<ClientCategory> getPage();
	
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
	public void delete(ClientCategory clientCategory);
	
	/**
	 * TODO: Commentary
	 * @param article
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ClientCategory save(ClientCategory clientCategory);
	
	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param page Nummer der Seite
	 * @param size Grösse der Seite
	 * @return Seite mit den Artikeln
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<ClientCategory> loadPage(int size);
	
	/**
	 * TODO: Commentary
	 * @param size
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
}
