package org.haegerp.entity.repository.supplier;

import java.util.List;
import org.haegerp.entity.Supplier;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die
 * Lieferanten bereitstellt.
 *
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface SupplierRepository extends MyRepository<Supplier, Long> {

    @Query(countQuery = "SELECT COUNT(*) "
                        + "FROM Supplier "
                        + "WHERE "
                        + "( "
                            + "(1 = ?1 AND TO_CHAR(taxId) LIKE '%' || UPPER(?2) || '%') "
                            + "OR (1 = ?1 AND (UPPER(name) LIKE '%' || UPPER(?2) || '%')) "
                            + "OR (1 = ?1 AND (UPPER(email) LIKE '%' || UPPER(?2) || '%')) "
                            + "OR (1 = ?1 AND (UPPER(city) LIKE '%' || UPPER(?2) || '%')) "
                            + "OR (1 = ?1 AND (UPPER(country) LIKE '%' || UPPER(?2) || '%')) "
                        + ") "
                        + "OR (1 = ?3)",
            value = "FROM Supplier "
                    + "WHERE "
                    + "( "
                        + "(1 = ?1 AND TO_CHAR(taxId) LIKE '%' || UPPER(?2) || '%') "
                        + "OR (1 = ?1 AND (UPPER(name) LIKE '%' || UPPER(?2) || '%')) "
                        + "OR (1 = ?1 AND (UPPER(email) LIKE '%' || UPPER(?2) || '%')) "
                        + "OR (1 = ?1 AND (UPPER(city) LIKE '%' || UPPER(?2) || '%')) "
                        + "OR (1 = ?1 AND (UPPER(country) LIKE '%' || UPPER(?2) || '%')) "
                    + ") "
                    + "OR (1 = ?3)")
    /**
     * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
     *
     * @param enableSearch 1 - True; 0 - False.
     * @param search Wert des Lieferanten, dass der Benutzer eingefügt.
     * @param enableAll Wenn andere "enable" Variablen 0 sind, dann muss diese
     * Variable 1 sein.
     * @param pageable PageRequest Klasse.
     * @return Eine Seite mit den Lieferanten.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<Supplier> findWithFilters(int enableSearch, String search,
            int enableAll, Pageable pageable);

    @Query(value = "FROM Supplier "
                    + "WHERE "
                    + "( "
                        + "(1 = ?1 AND TO_CHAR(taxId) LIKE '%' || UPPER(?2) || '%') "
                        + "OR (1 = ?1 AND (UPPER(name) LIKE '%' || UPPER(?2) || '%')) "
                        + "OR (1 = ?1 AND (UPPER(email) LIKE '%' || UPPER(?2) || '%')) "
                        + "OR (1 = ?1 AND (UPPER(city) LIKE '%' || UPPER(?2) || '%')) "
                        + "OR (1 = ?1 AND (UPPER(country) LIKE '%' || UPPER(?2) || '%')) "
                    + ") "
                    + "OR (1 = ?3)")
    /**
     * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
     *
     * @param enableSearch 1 - True; 0 - False.
     * @param search Wert des Lieferanten, dass der Benutzer eingefügt.
     * @param enableAll Wenn andere "enable" Variablen 0 sind, dann muss diese
     * Variable 1 sein.
     * @return Eine Liste mit den Lieferanten.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Supplier> findAllSuppliers(int enableSearch, String search,
            int enableAll);
}
