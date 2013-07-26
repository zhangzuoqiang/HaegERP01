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
					"OR (1 = ?7 AND UPPER(name) LIKE '%' || UPPER(?8) || '%') " +
					"OR (1 = ?9 AND TO_CHAR(priceVat*100) LIKE '%' || ?10 || '%') " +
					"OR (1 = ?11 AND TO_CHAR(priceGross) LIKE '%' || ?12 || '%') " +
					"OR (1 = ?13 AND TO_CHAR(priceSupplier) LIKE '%' || ?14 || '%') " +
					"OR (1 = ?15 AND TO_CHAR(stock) LIKE '%' || ?16 || '%') " +
				") " +
			") " +
			"OR (1 = ?17)",
			value="FROM Article " +
					"WHERE ((1 = ?1 AND idArticleCategory = ?2) OR 1=?3) " +
					"AND " +
					"( " +
						"(1 = ?4) " +
						"OR " +
						"( " +
							"(1 = ?5 AND TO_CHAR(ean) LIKE '%' || ?6 || '%') " +
							"OR (1 = ?7 AND UPPER(name) LIKE '%' || UPPER(?8) || '%') " +
							"OR (1 = ?9 AND TO_CHAR(priceVat*100) LIKE '%' || ?10 || '%') " +
							"OR (1 = ?11 AND TO_CHAR(priceGross) LIKE '%' || ?12 || '%') " +
							"OR (1 = ?13 AND TO_CHAR(priceSupplier) LIKE '%' || ?14 || '%') " +
							"OR (1 = ?15 AND TO_CHAR(stock) LIKE '%' || ?16 || '%') " +
						") " +
					") " +
					"OR (1 = ?17)")
	/**
	 * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
	 * 
	 * @param enableCategory 1 - True; 0 - False.
	 * @param idArticleCategory ID der Kategorie, dass der Benutzer gewählt hat.
	 * @param enableSearchCategory Keine suchen Kategorie? := 1; Sonst := 0
	 * @param enableSearchFilters Keine suchen Filter? := 1; Sonst := 0
	 * @param enableEan 1 - True; 0 - False.
	 * @param ean EAN des Artikel, dass der Benutzer eingefügt.
	 * @param enableName 1 - True; 0 - False.
	 * @param name Name des Artikel, dass der Benutzer eingefügt.
	 * @param enablePriceVat 1 - True; 0 - False.
	 * @param priceVat Preis MwSt. des Artikel, dass der Benutzer eingefügt.
	 * @param enablePriceGross 1 - True; 0 - False.
	 * @param priceGross Preis Brutto des Artikel, dass der Benutzer eingefügt.
	 * @param enablePriceSupplier 1 - True; 0 - False.
	 * @param priceSupplier Lieferantspreis des Artikel, dass der Benutzer eingefügt.
	 * @param enableStock 1 - True; 0 - False.
	 * @param stock Stock des Artikel, dass der Benutzer eingefügt.
	 * @param enableAll 1 - True; 0 - False.
	 * @param pageable PageRequest Klasse.
	 * @return Eine Seite mit den Artikeln.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Page<Article> findWithFilters(
			Integer enableCategory, Long idArticleCategory,
			Integer enableSearchCategory, Integer enableSearchFilters,
			Integer enableEan, String ean,
			Integer enableName, String name,
			Integer enablePriceVat, String priceVat,
			Integer enablePriceGross, String priceGross,
			Integer enablePriceSupplier, String priceSupplier,
			Integer enableStock, String stock,
			Integer enableAll,
			Pageable pageable);
	
}
