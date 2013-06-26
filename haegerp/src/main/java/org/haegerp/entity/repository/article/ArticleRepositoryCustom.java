package org.haegerp.entity.repository.article;

import org.haegerp.entity.Article;
import org.haegerp.exception.LengthOverflowException;

/**
 * Dieses Interface wird bei ArticleRepositoryCustomImpl bearbeitet.
 * 
 * @author Wolf
 *
 */
public interface ArticleRepositoryCustom {
	
	/**
	 * Diese Methode bearbeitet die neue Artikelversionen.
	 * 
	 * @param article - Der Artikel, die geändert wurde
	 */
	public void createArticleHistory(Article article) throws LengthOverflowException;
	
}
