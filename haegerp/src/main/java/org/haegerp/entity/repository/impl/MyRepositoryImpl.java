package org.haegerp.entity.repository.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.haegerp.entity.repository.MyRepository;
import org.haegerp.session.EmployeeSession;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
/**
 * Alle Datenbankoperationen werden in hier bearbeitet.<br/>
 * Alle Operationen werden in einer Tabelle mit einer Beschreibung und Mitarbeitersnummer gespeichert.<br/>
 * 
 * @author Wolf
 *
 * @param <T> - Entity
 * @param <ID> - ID
 */
public class MyRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID>{

	private EntityManager entityManager;
	
	public MyRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	@Modifying
	public void delete(T entity) {
		updateDateLastAction();
		super.delete(entity);
	}
	
	@Override
	@Transactional
	@Modifying
	public <S extends T> S save(S entity) {
		updateDateLastAction();
		return super.save(entity);
	}
	
	@Modifying
	@Transactional
	public void updateDateLastAction(){
		long idEmployee = EmployeeSession.getEmployee().getIdEmployee();
		
		Query query = entityManager.createQuery("UPDATE Employee e SET e.dateLastAction = ?1 where e.idEmployee = ?2");
		query.setParameter(1, new Date());
		query.setParameter(2, idEmployee);
		
		query.executeUpdate();
	}
}
