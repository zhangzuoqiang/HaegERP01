package org.haegerp.entity.repository.article;

import org.haegerp.entity.Article;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Artikel bereitstellt
 * 
 * @author Wolf
 *
 */
@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ArticleRepository extends MyRepository<Article, Long> {
	
	@Query(countQuery="SELECT COUNT(*) FROM Article WHERE articleCategory.idArticleCategory = ?1", value="FROM Article WHERE articleCategory.idArticleCategory = ?1")
	public Page<Article> findByIdArticleCategory(long idArticleCategory, Pageable pageable);
	
}
