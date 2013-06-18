package org.haegerp.entity.repository;

import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ArticleHistoryRepository extends JpaRepository<ArticleHistory, ArticleHistoryPK> {

	@Modifying
	@Transactional
	public <S extends ArticleHistory> S save(S entity);
	
	@Query(value="SELECT MAX(ah.articleHistoryPK.idArticleHistory) FROM ArticleHistory ah WHERE ah.articleHistoryPK.article.idArticle = ?1")
	public long findByIdArticle(long idArticle);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM ArticleHistory ah WHERE ah.articleHistoryPK.article.idArticle = ?1")
	public void deleteAllVersionsOfArticle(long idArticle);
}
