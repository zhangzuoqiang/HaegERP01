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
	/*
	@Query(countQuery="SELECT COUNT(*) " +
			"WHERE (1 = ?1 AND idArticleCategory = ?2)" +
				"OR (1 = ?3 AND ean = ?4)" +
				"OR (1 = ?5 AND name LIKE '%' || ?6 || '%')" +
				"OR (1 = ?7 AND (priceVat >= (?8 - 0.5) AND priceVat <= (?8 + 0.5)))" +
				"OR (1 = ?9 AND (priceGross >= (?10 - 5.0) AND priceGross <= (?10 + 5.0)))" +
				"OR (1 = ?11 AND (priceSupplier >= (?12 - 5.0) AND priceSupplier <= (?12 + 5.0)))" +
				"OR (1 = ?13 AND (stock >= (?14 - 10) AND stock <= (?14 + 10)))" +
				"OR (1 = ?15)",
			value="FROM Article" +
					"WHERE (1 = ?1 AND idArticleCategory = ?2)" +
						"OR (1 = ?3 AND ean = ?4)" +
						"OR (1 = ?5 AND name LIKE '%' || ?6 || '%')" +
						"OR (1 = ?7 AND (priceVat >= (?8 - 0.5) AND priceVat <= (?8 + 0.5)))" +
						"OR (1 = ?9 AND (priceGross >= (?10 - 5.0) AND priceGross <= (?10 + 5.0)))" +
						"OR (1 = ?11 AND (priceSupplier >= (?12 - 5.0) AND priceSupplier <= (?12 + 5.0)))" +
						"OR (1 = ?13 AND (stock >= (?14 - 10) AND stock <= (?14 + 10)))" +
						"OR (1 = ?15)")
	public Page<Article> findWithFilters(Pageable pageable);*/
	
}
