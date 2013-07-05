package org.haegerp.entity.repository.impl;

import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.haegerp.entity.repository.article.ArticleHistoryRepository;
import org.haegerp.entity.repository.article.ArticleRepositoryCustom;
import org.haegerp.exception.LengthOverflowException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Diese Implementierung bearbeitet die neue Artikelversionen.
 * Diese Klasse implementiert auch das Interface ArticleRepositoryCustom
 * 
 * @author Wolf
 *
 */
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom{
	
	@Autowired
	private ArticleHistoryRepository articleHistoryRepository;
	
	/**
	 * Diese Methode bearbeitet die neue Artikelversionen.
	 * 
	 * @param article - Der Artikel, die geändert wurde
	 */
	public void createArticleHistory(Article article) throws LengthOverflowException {
		try {
			
			Long articleHistoryNewID = articleHistoryRepository.findByIdArticle(article.getIdArticle());
			
			if (articleHistoryNewID == null)
				articleHistoryNewID = 1L;
			else
				articleHistoryNewID = articleHistoryNewID + 1L;
			
			ArticleHistory articleHistory = new ArticleHistory();
	        articleHistory.setArticleCategory(article.getArticleCategory().getName());
	        articleHistory.setArticleHistoryPK(new ArticleHistoryPK(articleHistoryNewID, article));
	        articleHistory.setEan(article.getEan());
	        articleHistory.setName(article.getName());
	        articleHistory.setPriceGross(article.getPriceGross());
	        articleHistory.setPriceSupplier(article.getPriceSupplier());
	        articleHistory.setPriceVat(article.getPriceVat());
	        articleHistory = articleHistoryRepository.save(articleHistory);
		} catch (LengthOverflowException ex) {
			throw ex;
		}
	}
}
