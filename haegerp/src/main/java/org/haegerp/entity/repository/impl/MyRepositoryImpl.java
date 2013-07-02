package org.haegerp.entity.repository.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.haegerp.entity.Log;
import org.haegerp.entity.repository.LogRepository;
import org.haegerp.entity.repository.MyRepository;
import org.haegerp.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private LogRepository logRepository;
	
	public MyRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}
	
	@Transactional
	@Modifying
	/**
	 * Methode, die eine Entität in der Datenbank erstellt.<br/>
	 * Das Log der Operation wird auch gespeichert.<br/>
	 * 
	 * @param entity Die Entität, die gespeichert wird.
	 */
	public <S extends T> S performNew(S entity) {
		Log log;
		log = new Log(entity.getClass().getSimpleName(), "New", Session.getEmployee());
		entityManager.persist(log);
		return super.save(entity);
	}
	
	@Transactional
	@Modifying
	/**
	 * Methode, die eine Entität in der Datenbank geändert.<br/>
	 * Das Log der Operation wird auch gespeichert.<br/>
	 * 
	 * @param entity Die Entität, die geändert wird.
	 */
	public <S extends T> S performEdit(S entity) {
		Log log;
		log = new Log(entity.getClass().getSimpleName(), "Edit", Session.getEmployee());
		entityManager.persist(log);
		return super.save(entity);
	}
	
	@Override
	@Transactional
	@Modifying
	public void delete(T entity) {
		Log log;
		log = new Log(entity.getClass().getSimpleName(), "Delete", Session.getEmployee());
		entityManager.persist(log);
		super.delete(entity);
	}
	
	@Deprecated
	@Override
	@Transactional
	@Modifying
	/**
	 * Bitte benutz performNew oder performEdit Methode
	 */
	public <S extends T> S save(S entity) {
		return performNew(entity);
	}
}
