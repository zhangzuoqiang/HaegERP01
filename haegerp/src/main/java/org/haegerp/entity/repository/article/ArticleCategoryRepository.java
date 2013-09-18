package org.haegerp.entity.repository.article;

import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die
 * Artikelkategorie bereitstellt
 *
 * @author Fabio Codinha
 *
 */
@Repository
@Transactional
public interface ArticleCategoryRepository extends MyRepository<ArticleCategory, Long> {

    @Query(countQuery = "SELECT COUNT (*) FROM ArticleCategory "
            + "WHERE (UPPER(name) LIKE '%' || UPPER(?1) || '%') "
            + "OR (UPPER(description) LIKE '%' || UPPER(?1) || '%') "
            + "OR (1 = ?2)",
            value = "FROM ArticleCategory "
            + "WHERE (UPPER(name) LIKE '%' || UPPER(?1) || '%') "
            + "OR (UPPER(description) LIKE '%' || UPPER(?1) || '%') "
            + "OR (1 = ?2)")
    /**
     * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
     *
     * @param textToFilter Text, dass der Benutzer einge
     * @param enableAll Wenn TextToFilter keinen Text hat, dann muss diese
     * Variable 0 sein, sonst ist 1.
     * @param pageable PageRequest Klasse.
     * @return Eine Seite mit den Artikelkategorien.
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Page<ArticleCategory> findWithFilters(String textToFilter, int enableAll, Pageable pageable);
}
