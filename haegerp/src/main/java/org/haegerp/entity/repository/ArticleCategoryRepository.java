package org.haegerp.entity.repository;

import org.haegerp.entity.ArticleCategory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ArticleCategoryRepository extends MyRepository<ArticleCategory, Long> {
	
}
