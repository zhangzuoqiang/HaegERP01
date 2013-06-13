package org.haegerp.entity.repository;

import org.haegerp.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer> {
	
	@Modifying
	@Transactional
	public <S extends ArticleCategory> S save(S entity);
}
