package org.haegerp.entity.repository.article;

import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Artikelversion bereitstellt.
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface ArticleHistoryRepository extends MyRepository<ArticleHistory, ArticleHistoryPK> {
	
	/**
	 * Die Methode findet die letzte Version von dem Artikel.
	 * 
	 * @param idArticle ID des Artikel.
	 * @return ID von der letzten Version.
	 */
	@Query(value="SELECT MAX(ah.articleHistoryPK.idArticleHistory) FROM ArticleHistory ah WHERE ah.articleHistoryPK.article.idArticle = ?1")
	public Long findByIdArticle(long idArticle);
	
	/**
	 * Diese Methode löcht alle Versionen von einem Artikel.
	 * 
	 * @param idArticle ID des Artikel.
	 */
	@Modifying
	@Transactional
	@Query(value="DELETE FROM ArticleHistory ah WHERE ah.articleHistoryPK.article.idArticle = ?1")
	public void deleteAllVersionsOfArticle(long idArticle);
}
