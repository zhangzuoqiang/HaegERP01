package org.haegerp.entity.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface gibt das Verhalten zu alle Repositories, dass man hier implementiert
 * 
 * @author Wolf
 *
 * @param <T> - Entität
 * @param <ID> - ID (Typ vom Primary Key)
 */
@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
	
	@Modifying
	@Transactional
	public void delete(T entity);
	
	@Modifying
	@Transactional
	public <S extends T> S save(S entity);
}
