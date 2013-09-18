package org.haegerp.entity.repository;

import org.haegerp.entity.Log;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Wann man eine Änderung in die Datenbank macht, dann muss die Änderung aufgezeichnet werden
 * 
 * @author Fabio Codinha
 *
 */
@Repository
@Transactional
public interface LogRepository extends MyRepository<Log, Long> {

}
