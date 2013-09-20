package org.haegerp.service;

import java.util.List;

import org.haegerp.entity.ArticleCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface ArticleCategoryService {
	
	/**
	 * Bekommt alle Kategorien in einer Liste.
	 * @return Liste mit den Kategorien
	 */
	public List<ArticleCategory> getAllCategories();
	
	/**
	 * Holt die Seite, die zum Benutzer gezeigt wird
	 * @return Aktueller Stand der Seite
	 */
	public Page<ArticleCategory> getPage();
	
	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Artikelkategorien will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);
	
	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele Artikelkategorien will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);
	
	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele Artikelkategorien will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Artikelkategorien will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);
	
	/**
	 * Die Artikelkategorie wird gelöscht
	 * @param articleCategory Die Artikelkategorie
	 */
	public void delete(ArticleCategory articleCategory);
	
	/**
	 * Die Artikelkategorie wird gespeichert
	 * @param articleCategory Die Artikelkategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ArticleCategory save(ArticleCategory articleCategory);
	
	/**
	 * Eine neue Seite wird geholt
	 * 
	 * @param size Wie viele Artikelkategorien will der Benutzer in der Seite
	 * @return Seite mit den Artikelkategorien
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<ArticleCategory> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Artikelkategorie will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
	/**
	 * Holt eine Artikelkategorie.
	 * @param id ID der Kategorie
	 * @return Die Artikelkategorie.
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ArticleCategory getArticleCategoryById(long id);
        
        /**
         * Hat die Artikelkategorie keine Artikeln?
         * @param idCategory ID der Artikelkategorie.
         * @return True - Wenn die Kategorie keinen Artikel hat; False - Sonst.
         */
        @Transactional(propagation=Propagation.REQUIRED)
        public boolean isCategoryArticlesEmpty(long idCategory);
}
