package org.haegerp.service;

import java.util.List;

import org.haegerp.entity.SalaryCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface SalaryCategoryService {
	
	/**
	 * Bekommt alle Kategorien in einer Liste.
	 * @return Liste mit den Kategorien
	 */
	public List<SalaryCategory> getAllCategories();
	
	/**
	 * Holt die Seite
	 * @return Aktueller Stand der Seite
	 */
	public Page<SalaryCategory> getPage();
	
	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Gehaltskategorien will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);
	
	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele Gehaltskategorien will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);
	
	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele Gehaltskategorien will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Gehaltskategorien will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);
	
	/**
	 * Die Gehaltskategorie wird gelöscht
	 * @param salaryCategory Die Salarykategorie
	 */
	public void delete(SalaryCategory salaryCategory);
	
	/**
	 * Die Gehaltskategorien wird gespeichert
	 * @param salaryCategory Die Artikelkategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public SalaryCategory save(SalaryCategory salaryCategory);
	
	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param size Wie viele Gehaltskategorien will der Benutzer in der Seite
	 * @return Seite mit den Gehaltskategorien
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<SalaryCategory> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
         * 
	 * @param size Wie viele Gehaltskategorien will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
	/**
	 * Holt eine Gehaltskategorie
         * 
	 * @param id ID der Gehaltskategorie
	 * @return Die Gehaltskategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public SalaryCategory getSalaryCategoryById(long id);
        
        /**
         * Hat die Gehaltskategorie keine Mitarbeiter?
         * 
         * @param idCategory ID der Gehaltkategorie.
         * @return True - Wenn die Kategorie keinen Mitarbeiter hat; False - Sonst.
         */
        @Transactional(propagation=Propagation.REQUIRED)
        public boolean isSalaryCategoryEmpty(long idCategory);
}
