package org.haegerp.entity.repository.article;

import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.stereotype.Repository;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Artikelkategorie bereitstellt
 * 
 * @author Wolf
 *
 */
@Repository
public interface ArticleCategoryRepository extends MyRepository<ArticleCategory, Long> {
	
}
