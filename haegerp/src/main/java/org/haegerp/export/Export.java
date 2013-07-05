package org.haegerp.export;

import java.util.List;

import org.haegerp.entity.Article;

/**
 * Dieses Interface macht den Export zu Dateien.
 * 
 * @author Fabio Codinha
 *
 */
public interface Export {
	/**
	 * Die Liste der Artikel wird in Dateien gespeichert
	 * 
	 * @param articles Die Artikel, die die Datei 
	 * @return Wenn die Operation erfolgreich war - True; sonst - False
	 * @throws Exception Wann ein Problem bekommt wird
	 */
	public boolean export(List<Article> articles) throws Exception;
	
}
