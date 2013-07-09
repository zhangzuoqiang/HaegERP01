package org.haegerp.entity.repository.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

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
	
}
