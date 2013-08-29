package org.haegerp.controller;

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
 * @author Wolf
 *
 */
@Service
@Transactional
public interface ArticleController {
	
	/**
	 * Holt die Seite
	 * @return Aktueller Staat der Seite
	 */
	public Page<Article> getPage();
	
	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Linea will der Benutzer in der Seite
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
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param idArticleCategory Welche Kategorie, die der Benutzer gewählt hat
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 */
	public void setCategory(Long idArticleCategory, int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param field Welches Feld, das der Benutzer suchen will
	 * @param size Wie viele Linea will der Benutzer in der Seite
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
	 * Eine neue Seite wird erhalt
	 * 
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return Seite mit den Artikeln
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<Article> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadAllTableRows();
	
	/**
	 * Eine Liste wird erhalt
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
	 * @param id ID des Artikel
	 * @return Der Artikel
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Article getArticleById(long id);
}
