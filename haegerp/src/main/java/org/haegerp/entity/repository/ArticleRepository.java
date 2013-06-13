package org.haegerp.entity.repository;

import org.haegerp.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	@Modifying
	@Transactional
	public <S extends Article> S save(S entity);
}
