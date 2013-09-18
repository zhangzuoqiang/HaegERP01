package org.haegerp.entity.repository.employee;

import org.haegerp.entity.Employee;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die
 * Mitarberter bereitstellt
 *
 * @author Fabio Codinha
 *
 */
@Repository
@Transactional
public interface EmployeeRepository extends MyRepository<Employee, Long> {

    @Query("FROM Employee "
            + "WHERE Username = ?1 AND Password = ?2")
    /**
     * Diese Methode pruft die Anmeldung.
     *
     * @param username Benutzername, der der Benutzer geschrieben hat
     * @param passwordMD5 Kennwort in MD5, das der Benutzer geschrieben hat
     * @return Employee - Benutzername und Kennwort sind richtig, sonst Null
     */
    public Employee login(String username, String passwordMD5);

    @Query(countQuery = "SELECT COUNT(*) "
            + "FROM Employee "
            + "WHERE ("
            + "(1=?1) "
            + "OR "
            + "( "
            + "(1 = ?2 OR idDivision = ?3) "
            + "AND (1 = ?4 OR idSalaryCategory = ?5) "
            + "AND (1 = ?6 OR idUserGroup = ?7) "
            + ") "
            + ") "
            + "AND "
            + "( "
            + "(1 = ?8) "
            + "OR "
            + "( "
            + "(1 = ?9 AND TO_CHAR(IdCard) LIKE '%' || ?10 || '%') "
            + "OR (1 = ?9 AND UPPER(Name) LIKE '%' || UPPER(?10) || '%') "
            + "OR (1 = ?9 AND UPPER(Email) LIKE '%' || UPPER(?10) || '%') "
            + "OR (1 = ?9 AND UPPER(City) LIKE '%' || UPPER(?10) || '%') "
            + ") "
            + ") "
            + "OR (1 = ?11)",
            value = "FROM Employee "
            + "WHERE ("
            + "(1=?1) "
            + "OR "
            + "( "
            + "(1 = ?2 OR idDivision = ?3) "
            + "AND (1 = ?4 OR idSalaryCategory = ?5) "
            + "AND (1 = ?6 OR idUserGroup = ?7) "
            + ") "
            + ") "
            + "AND "
            + "( "
            + "(1 = ?8) "
            + "OR "
            + "( "
            + "(1 = ?9 AND TO_CHAR(IdCard) LIKE '%' || ?10 || '%') "
            + "OR (1 = ?9 AND UPPER(Name) LIKE '%' || UPPER(?10) || '%') "
            + "OR (1 = ?9 AND UPPER(Email) LIKE '%' || UPPER(?10) || '%') "
            + "OR (1 = ?9 AND UPPER(City) LIKE '%' || UPPER(?10) || '%') "
            + ") "
            + ") "
            + "OR (1 = ?11)")
    /**
     * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
     *
     * @param disableSearchCategory 1 - True; 0 - False.
     * @param enableDivision 1 - True; 0 - False.
     * @param idDivision ID der Division.
     * @param enableSalaryCategory 1 - True; 0 - False.
     * @param idSalaryCategory ID der Gehaltskategorie.
     * @param enableUserGroup 1 - True; 0 - False.
     * @param idUserGroup ID der Benutzergruppe
     * @param disableSearchFilters 1 - True; 0 - False.
     * @param enableSearch 1 - True; 0 - False.
     * @param search
     * @param enableAll 1 - True; 0 - False.
     * @param pageable PageRequest Klasse.
     * @return Eine Seite mit den Mitarbeitern
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Page<Employee> findWithFilters(int disableSearchCategory,
            int enableDivision, Long idDivision,
            int enableSalaryCategory, Long idSalaryCategory,
            int enableUserGroup, Long idUserGroup,
            int disableSearchFilters,
            int enableSearch, String search,
            int enableAll, Pageable pageable);
}
