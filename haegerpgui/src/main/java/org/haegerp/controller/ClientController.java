package org.haegerp.controller;

import org.haegerp.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von dem Kunden.
 * 
 * @author Wolf
 *
 */
@Service
@Transactional
public interface ClientController {

	/**
	 * TODO: Commentary
	 * @return
	 */
	public Page<Client> getPage();

	/**
	 * TODO: Commentary
	 * @param value
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Object[][] loadTableRows(int value);

	/**
	 * TODO: Commentary
	 * @param text
	 * @param selectedIndex
	 * @param value
	 */
	public void setSearch(String text, int selectedIndex, int value);

	/**
	 * TODO: Commentary
	 * @param cbbIndex
	 * @param value
	 */
	public void setCategory(long cbbIndex, int value);

	/**
	 * TODO: Commentary
	 * @param value
	 */
	public boolean getNextPage(int size);

	/**
	 * TODO: Commentary
	 * @param value
	 */
	public boolean getPreviousPage(int size);

	/**
	 * TODO: Commentary
	 * @param value
	 */
	public boolean getFirstPage(int size);

	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param page Nummer der Seite
	 * @param size Grösse der Seite
	 * @return Seite mit den Kunden
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<Client> loadPage(int size);
}
