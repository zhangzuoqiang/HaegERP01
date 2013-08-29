package org.haegerp.entity.repository.article;

import org.haegerp.entity.Article;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Artikel bereitstellt
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface ArticleRepository extends MyRepository<Article, Long> {
	
	@Query(countQuery="SELECT COUNT(*) " +
			"FROM Article " +
					"WHERE ((1 = ?1 AND idArticleCategory = ?2) OR 1=?3) " +
					"AND " +
					"( " +
						"(1 = ?4) " +
						"OR " +
						"( " +
							"(1 = ?5 AND TO_CHAR(ean) LIKE '%' || ?6 || '%') " +
							"OR (1 = ?5 AND UPPER(name) LIKE '%' || UPPER(?6) || '%') " +
							"OR (1 = ?5 AND TO_CHAR(priceVat*100, '999.99') LIKE '%' || ?6 || '%') " +
							"OR (1 = ?5 AND TO_CHAR(priceGross, '99999999999999999.99') LIKE '%' || ?6 || '%') " +
							"OR (1 = ?5 AND TO_CHAR(priceSupplier, '99999999999999999.99') LIKE '%' || ?6 || '%') " +
							"OR (1 = ?5 AND TO_CHAR(stock) LIKE '%' || ?6 || '%') " +
						") " +
					") " +
					"OR (1 = ?7)",
			value="FROM Article " +
					"WHERE ((1 = ?1 AND idArticleCategory = ?2) OR 1=?3) " +
					"AND " +
					"( " +
						"(1 = ?4) " +
						"OR " +
						"( " +
							"(1 = ?5 AND TO_CHAR(ean) LIKE '%' || ?6 || '%') " +
							"OR (1 = ?5 AND UPPER(name) LIKE '%' || UPPER(?6) || '%') " +
							"OR (1 = ?5 AND TO_CHAR(priceVat*100, '999.99') LIKE '%' || ?6 || '%') " +
							"OR (1 = ?5 AND TO_CHAR(priceGross, '99999999999999999.99') LIKE '%' || ?6 || '%') " +
							"OR (1 = ?5 AND TO_CHAR(priceSupplier, '99999999999999999.99') LIKE '%' || ?6 || '%') " +
							"OR (1 = ?5 AND TO_CHAR(stock) LIKE '%' || ?6 || '%') " +
						") " +
					") " +
					"OR (1 = ?7)")
	/**
	 * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
	 * 
	 * @param enableCategory 1 - True; 0 - False.
	 * @param idArticleCategory ID der Kategorie, dass der Benutzer gewählt hat.
	 * @param enableSearchCategory Keine suchen Kategorie? := 1; Sonst := 0
	 * @param enableSearchFilters Keine suchen Filter? := 1; Sonst := 0
	 * @param enableSearch 1 - True; 0 - False.
	 * @param search Suchen, dass der Benutzer im Feld eingefügt hat.
	 * @param enableAll 1 - True; 0 - False.
	 * @param pageable PageRequest Klasse.
	 * @return Eine Seite mit den Artikeln.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Page<Article> findWithFilters(
			Integer enableCategory, Long idArticleCategory,
			Integer enableSearchCategory, Integer enableSearchFilters,
			Integer enableSearch, String search,
			Integer enableAll,
			Pageable pageable);
	
}
