package org.haegerp.service;

import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface ArticleHistoryService {
	
	/**
	 * Die Methode holt die lezte Version von einem Artikel.
	 * 
	 * @param idArticle ID des Artikel
	 * @return Die lezte Version des Artikel
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Long getLastVersion(long idArticle);
	
	/**
	 * Holt die Artikelkategorie
	 * 
	 * @param articleHistoryPK ID der Artikelkategorie
	 * @return Artikelkategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ArticleHistory getArticleHistoryById(ArticleHistoryPK articleHistoryPK);
}
