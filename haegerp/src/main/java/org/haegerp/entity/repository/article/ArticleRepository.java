package org.haegerp.entity.repository.article;

import org.haegerp.entity.Article;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Artikel bereitstellt
 * 
 * @author Wolf
 *
 */
@Repository
public interface ArticleRepository extends MyRepository<Article, Long> {
	
	@Query(countQuery="SELECT COUNT(*) " +
						"FROM Article " +
						"WHERE ((1 = ?1 AND idArticleCategory = ?2) OR 1=?3) " +
						"AND " +
						"( " +
							"(1 = ?4) " +
							"OR " +
							"( " +
								"(1 = ?5 AND ean >= ?6 AND ean <= ?7) " +
								"OR (1 = ?8 AND name LIKE '%' || ?9 || '%') " +
								"OR (1 = ?10 AND (priceVat >= (?11 - 0.5) AND priceVat <= (?11 + 0.5))) " +
								"OR (1 = ?12 AND (priceGross >= (?13 - 5.0) AND priceGross <= (?13 + 5.0))) " +
								"OR (1 = ?14 AND (priceSupplier >= (?15 - 5.0) AND priceSupplier <= (?15 + 5.0))) " +
								"OR (1 = ?16 AND (stock >= (?17 - 10) AND stock <= (?17 + 10))) " +
							") " +
						") " +
						"OR (1 = ?18)",
			value="FROM Article " +
					"WHERE ((1 = ?1 AND idArticleCategory = ?2) OR 1=?3) " +
					"AND " +
					"( " +
						"(1 = ?4) " +
						"OR " +
						"( " +
							"(1 = ?5 AND ean >= ?6 AND ean <= ?7) " +
							"OR (1 = ?8 AND name LIKE '%' || ?9 || '%') " +
							"OR (1 = ?10 AND (priceVat >= (?11 - 0.5) AND priceVat <= (?11 + 0.5))) " +
							"OR (1 = ?12 AND (priceGross >= (?13 - 5.0) AND priceGross <= (?13 + 5.0))) " +
							"OR (1 = ?14 AND (priceSupplier >= (?15 - 5.0) AND priceSupplier <= (?15 + 5.0))) " +
							"OR (1 = ?16 AND (stock >= (?17 - 10) AND stock <= (?17 + 10))) " +
						") " +
					") " +
					"OR (1 = ?18)")
	/**
	 * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
	 * 
	 * @param enableCategory 1 - True; 0 - False.
	 * @param idArticleCategory ID der Kategorie, dass der Benutzer gewählt hat.
	 * @param enableSearchCategory Keine suchen Kategorie? := 1; Sonst := 2
	 * @param enableSearchFilters Keine suchen Filter? := 1; Sonst := 2
	 * @param enableEan 1 - True; 0 - False.
	 * @param eanMinimum EAN des Artikel, dass der Benutzer eingefügt. z.B. (Benutzer einfügt: 560, eanMin: 5600000000000)
	 * @param eanMaximum EAN des Artikel, dass der Benutzer eingefügt. z.B. (Benutzer einfügt: 560, eanMax: 5609999999999)
	 * @param enableName 1 - True; 0 - False.
	 * @param name Name des Artikel, dass der Benutzer eingefügt.
	 * @param enablePriceVat 1 - True; 0 - False.
	 * @param priceVat Preis MwSt. des Artikel, dass der Benutzer eingefügt.
	 * @param enablePriceGross 1 - True; 0 - False.
	 * @param priceGross Preis Brutto des Artikel, dass der Benutzer eingefügt.
	 * @param enablePriceSupplier 1 - True; 0 - False.
	 * @param priceSupplier Lieferantspreis des Artikel, dass der Benutzer eingefügt.
	 * @param enableStock 1 - True; 0 - False.
	 * @param stock Stock des Artikel.
	 * @param enableAll Wenn andere "enable" Variablen 0 sind, dann muss diese Variable 1 sein.
	 * @param pageable PageRequest Klasse.
	 * @return Eine Seite mit den Artikeln.
	 */
	public Page<Article> findWithFilters(
			Integer enableCategory, Long idArticleCategory,
			Integer enableSearchCategory, Integer enableSearchFilters,
			Integer enableEan, Long eanMinimum, Long eanMaximum,
			Integer enableName, String name,
			Integer enablePriceVat, Double priceVat,
			Integer enablePriceGross, Double priceGross,
			Integer enablePriceSupplier, Double priceSupplier,
			Integer enableStock, Integer stock,
			Integer enableAll,
			Pageable pageable);
	
}
