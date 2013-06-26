package org.haegerp.entity.repository.article;

import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Artikelkategorie bereitstellt
 * 
 * @author Wolf
 *
 */
@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ArticleCategoryRepository extends MyRepository<ArticleCategory, Long> {
	
}
