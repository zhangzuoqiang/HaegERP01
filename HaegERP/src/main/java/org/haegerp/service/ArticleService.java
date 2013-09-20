package org.haegerp.service;

import java.util.List;

import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von dem Artikel.
 * 
 * @author Fabio Codinha
 *
 */
@Service
@Transactional
public interface ArticleService {
	
	/**
	 * Holt die Seite
	 * @return Aktueller Stand der Seite
	 */
	public Page<Article> getPage();
	
	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Artikeln will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);
	
	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);
	
	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele Artikeln will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param idArticleCategory Welche Kategorie, die der Benutzer gewählt hat
	 * @param size Wie viele Artikeln will der Benutzer in der Seite
	 */
	public void setCategory(Long idArticleCategory, int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Artikeln will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);
	
	/**
	 * Der Artikel wurde gelöscht
	 * @param article Der Artikel
	 */
	public void delete(Article article);
	
	/**
	 * Der Artikel wurde gespeichert
	 * @param article Der Artikel
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Article save(Article article);
	
	/**
	 * Eine neue Seite wird geholt
	 * 
	 * @param size Wie viele Artikeln will der Benutzer in der Seite
	 * @return Seite mit den Artikeln
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<Article> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
         * 
	 * @param size Wie viele Artikeln will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
        /**
         * Diese Methode holt alle Werte und wickelt den Inhalt der Tabelle ab
         * 
         * @param enableSearch 1 - Wenn das Feld 'Search' etwas hat; 0 - Sonst
         * @param search Suchen, die der Benutzer eingefügt hat
         * @param enableAll 1 - Wenn das Feld 'enableSearch' 0 ist; 0 - Sonst
         * @return Objekt mit dem Inhalt von der Tabelle
         */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadAllTableRows(Integer enableSearch, String search, Integer enableAll);
	
	/**
	 * Eine Liste wird geholt
	 * 
	 * @return Liste mit den Artikeln
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Article> loadAllArticles();
	
	/**
	 * Diese Methode löscht alle Artikeln von einer Kategorie
	 * @param articleCategory Artikelkategorie
	 */
	public void deleteAllArticleFromCategory(ArticleCategory articleCategory);
	
	/**
	 * Holt einen Artikel
	 * 
	 * @param id ID des Artikels
	 * @return Der Artikel
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Article getArticleById(long id);
}
