package org.haegerp.controller;

import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von dem Artikel.
 * 
 * @author Wolf
 *
 */
@Service
@Transactional
public interface ArticleController {
	
	/**
	 * TODO: Commentary
	 * @return
	 */
	public Page<Article> getPage();
	
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
	 * @param idArticleCategory
	 * @param size
	 */
	public void setCategory(Long idArticleCategory, int size);
	
	/**
	 * TODO: Commentary
	 * @param value
	 * @param field
	 * @param size
	 */
	public void setSearch(String value, int field, int size);
	
	/**
	 * TODO: Commentary
	 * @param article
	 */
	public void delete(Article article);
	
	/**
	 * TODO: Commentary
	 * @param article
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Article save(Article article);
	
	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param page Nummer der Seite
	 * @param size Grösse der Seite
	 * @return Seite mit den Artikeln
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<Article> loadPage(int size);
	
	/**
	 * TODO: Commentary
	 * @param size
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
	/**
	 * TODO: Commentary
	 * @param articleCategory
	 */
	public void deleteAllArticleFromCategory(ArticleCategory articleCategory);
}
