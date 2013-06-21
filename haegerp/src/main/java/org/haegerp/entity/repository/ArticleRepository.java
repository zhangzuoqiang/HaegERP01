package org.haegerp.entity.repository;

import org.haegerp.entity.Article;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ArticleRepository extends MyRepository<Article, Long>, ArticleRepositoryCustom {
	
}
