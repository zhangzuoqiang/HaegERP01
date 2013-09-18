package org.haegerp.entity.repository.employee;

import org.haegerp.entity.UserGroup;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die
 * Benutzergruppen bereitstellt.
 *
 * @author Fabio Codinha
 *
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface UserGroupRepository extends MyRepository<UserGroup, Long> {

    @Query(countQuery = "SELECT COUNT(*) FROM UserGroup "
            + "WHERE (UPPER(name) LIKE '%' || UPPER(?1) || '%') "
            + "OR (UPPER(description) LIKE '%' || UPPER(?1) || '%') "
            + "OR (1 = ?2)",
            value = "FROM UserGroup "
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
    public Page<UserGroup> findWithFilters(String textToFilter, int enableAll, Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRED)
    public UserGroup findOne(Long id);
}
